package com.kke.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Qualifier;

import org.springframework.stereotype.Service;

import com.kke.dao.ScrambleDAO;
import com.kke.vo.ApiVO;
import com.kke.vo.BoardVO;
import com.kke.vo.LinkVO;
import com.kke.vo.MemberVO;
import com.kke.vo.ScrambleVO;

@Service
public class ScrambleServiceImpl implements ScrambleService {

	@Inject
	ScrambleDAO dao;
	
	public void updateDetailForDate(ApiVO api) {
		Date date;
		
		//System.out.println("store : " + api.getBank_store());
		
		if(!api.getBank_store().equals(""))
			date = StringToDate(api.getBank_store());
		else 
			date = scrambleDate();
		
		String sc_acc_num = dao.searchScAccNum(api.getAcc_num());
		ApiVO vo = createBoard();
		vo.setAcc_num(sc_acc_num);
		vo.setAcc_code(api.getAcc_code());
		vo.setBank_date(date);
		
		// 중복확인
		// 여기서 중복확인해야할 것은 이미 기록이 있다면, 해당 Balance가 얼마인지?
		
		if (api.getBank_deposit().equals("입금"))
			api.setBank_balance(dao.getBalance(api.getAcc_num(),api.getAcc_code()) + api.getBank_amount());
		else
			api.setBank_balance(dao.getBalance(api.getAcc_num(),api.getAcc_code()) - api.getBank_amount());
		
		dao.updateDetailForDate(vo);
	}
	//Auto를 모두 ScrambleService로 옮겨야할까?
	
	
	
	public List<BoardVO> scrambledList(List<BoardVO> voList) {
		List<String> scNumList;
		
		scNumList = dao.matchAccNum(voList);
		return dao.matchAccDeatil(scNumList, voList);
	}
	
	@Override
	public void updateDetail(ApiVO api, int bank_code) throws Exception {
		String sc_acc_num = dao.searchScAccNum(api.getAcc_num());

		if (api.getBank_deposit().equals("입금"))
			api.setBank_balance(dao.getBalance(api.getAcc_num(),api.getAcc_code()) + api.getBank_amount());
		else
			api.setBank_balance(dao.getBalance(api.getAcc_num(),api.getAcc_code()) - api.getBank_amount());
		api.setAcc_code(bank_code);
		api.setAcc_num(sc_acc_num);
		api.setBank_no(dao.getLastNo()+1);
		dao.updateDetail(api);
	}
		
	@Override
	public boolean setLinkDBTest(String acc_num, int bank_code) throws Exception {
		// LinkDB에 들어갈 것 : 이름 , 핸드폰번호, 원본DB의 acc_num. -> acc_num을 가지고 핸드폰번호 이름 찾아내야함 -> update할 때 계좌 추가때 해야할 일
		// 나중에 API접속이후 phoneID들어갈거야					  -> 우리형 화이팅
		// 지금은 한개 acc_num만 넣어주자.
		// Test 이후 모든 이름과 핸드폰번호를 넣어줘야 함.				-> 하지만 현재 이 코드도 살려둘거야, update해줄때! 
															// update는 두개로 나뉜다 1. 계좌 추가 2. 계좌세부사항변경
		//System.out.println("Bank_CODE : " + bank_code);
		
		if(dao.nestAccNumChk(acc_num) != 0) {
			if (dao.isScNumChk(acc_num).equals("-1"))
				return true;
			else 
				return false;
			// false인 얘네들은 계좌세부내역만 update되면 된다.
			// 이 부분은 따로 처리하는게 좋을 듯 싶어 이렇게 false로 한다. -> accDetailInfo 뜯어내지않는다.
		}
		// 원래 false였는데, Conn이 자동 생성된다는 상황 
		// 이게 true가 되면 생기는 문제 : Scramble에 들어가는 값들은 중복이 일어날 수 밖에 없다.
		// 왜냐면 이미 있는건지 아닌지 구분 불가능이니까
		// 이게 false라면, 새로운 Acc이 들어오면 그에 대한 AccDetailInfo처리까지는 버튼을 해도 할 수 있도록 했으니까
		
		
//		LinkVO vo = new LinkVO();
//		vo.setAcc_num(acc_num);
//		vo.setAcc_code(bank_code);
//		
//		List<String>member = dao.searchMember(acc_num, bank_code);
//		vo.setUser_name(member.get(0));
//		vo.setUser_phoneNum(member.get(1));
//		
//		System.out.println("in SetLinkDBTest, phoneNum : " + vo.getUser_phoneNum());
//		dao.insertLink(vo);
		// 이부분은 trigger로 해결했음
		
		return true;
	}
	
	@Override
	public int scrambleBank(String acc_num,int bank_code, int bank_no) throws Exception {
		// LinkDB에서 acc_num을 가져와 (acc_num만 있으면 된다. 이름과 핸드폰번호 불필요)
		// 해당 acc_num을 바꿔!
		// DB에 Update해!
		String code = "";
		
		if(bank_code == 4)
			code = "004";
		else if (bank_code == 11)
			code = "011";
		else
			code = "081";
			
		String sc_num = scrambleAccount(code);
		//System.out.println("sc_num : " + sc_num);
		
		// sc_acc_num 중복체크
		while(dao.nestScAccNumChk(sc_num))
			sc_num = scrambleAccount(code);
		
		
		dao.insertScAccNum(acc_num,sc_num);
		
		
		// 1. 원본 acc_num으로 Detail List가져와
		List<BoardVO>voList = dao.searchDetailInfo(acc_num,bank_code);
		
		for(BoardVO vo : voList) {
			vo.setAcc_num(sc_num);
			if (vo.getBank_deposit().equals("입금")) 
				vo.setBank_store(scrambleName("내 계좌에도 누군가 입금 해주겠지?"));
			else 
				vo.setBank_store(scrambleStore());
			vo.setBank_no(++bank_no);
			vo.setAcc_code(bank_code);
		}
		
		dao.insertScrableDetail(voList);
		// 2. List에서 Store만 바꿔서 Scramble Detail로 넣어줘!
		// 3. ?
		return bank_no;
		
	}
	
	// 3개 은행에 대해 Link 한다.	
	public void setLinkDB() throws Exception {
		int bank_no=0;
		
		clearSDb();
		
		bank_no = setLinkDbBank(11, bank_no);
		bank_no = setLinkDbBank(4, bank_no);
		bank_no = setLinkDbBank(81, bank_no);
	}
	
	private void clearSDb() {
		dao.clearSDb();
		
	}
	// 
	private int setLinkDbBank(int bank_code, int bank_no) throws Exception {
		List<String> accNumList = dao.getAccounts(bank_code);
		for(String acc_num : accNumList) {
			if (setLinkDBTest(acc_num,bank_code))
				bank_no = scrambleBank(acc_num,bank_code, bank_no);
		}
		return bank_no;
	}
	
	
	
	
	
	
	
	
	
	
	

	public MemberVO scrambleData(MemberVO vo) throws Exception {
		//ScrambleVO sVo = new ScrambleVO();
		MemberVO scrambledVo = new MemberVO();

		//sVo.setName(vo.getuser_id());
		//sVo.setRrn(vo.getuser_RRN());
		//sVo.setPhoneNum(vo.getuser_phoneNum());

		//dao.insertRaw(sVo);
		

		//System.out.println("raw NAME RRN PHONENUM " + sVo.getName() + "\n" + sVo.getRrn() + "\n" + sVo.getPhoneNum());

		scrambledVo.setuser_name(scrambleName(vo.getuser_name()));
		scrambledVo.setuser_RRN(scrambleRrn(vo.getuser_RRN()));
		scrambledVo.setuser_phoneNum(scramblePhoneNum(vo.getuser_phoneNum()));
		
		
		//scramblePhoneNum("love");
		//scrambleAccount("12345678901234");
		
		//System.out.println("scramble NAME RRN PHONENUM " + scrambledVo.getName() + "\n" + scrambledVo.getRrn() + "\n" + scrambledVo.getPhoneNum());
		
		return scrambledVo;
		
		//TEST용도였음! 
		//dao.insertScramble(scrambledVo);
	}

	public ScrambleVO createData() throws Exception {
		ScrambleVO sVo = new ScrambleVO();
		return sVo;
	}

	
	String scrambleName(String str) { String name = "";
 
	String[] last = {"김","이","박","최","정","강","조","윤","장","임",
	"한","오","서","신","권","황","안","송","전","홍",
	"유","고","문","양","손","배","조","백","허","유",
	"남","심","노","정","하","곽","성","차","주","우","제갈"}; 
	String[] firstName = {"민준", "서준",														
	"예준", "도윤", "시우", "주원", "하준", "지호", "지후", "준서", "준우", "현우", "지훈", "도현", "건우", 
	"우진", "민재", "현준", "선우", "서진", "연우", "정우", "유준", "승현", "승우", "준혁", "지환", "시윤", 
	"승민", "유찬", "지우", "은우", "민성", "준영", "시후", "진우", "윤우", "지원", "수현", "동현", "재윤", 
	"시현", "민규", "태윤", "재원", "재민", "민우", "은찬", "한결", "시원", "건우", // 남 & 녀 "서연",		 
	"서윤", "지우", "서현", "민서", "하은", "하윤", "윤서", "지민", "지유", "채원", "지윤", "은서", "수아", 
	"다은", "예은", "수빈", "지아", "소율", "예원", "지원", "예린", "소윤", "시은", "유진", "지안", "하린", 
	"채은", "가은", "서영", "윤아", "민지", "유나", "예진", "수민", "수연", "연우", "예나", "예서", "주아", 
	"서아", "시연", "연서", "현서", "다인", "하율", "다연", "아인", "서은", "서진", "서아"};				 
	double rand = Math.random()*100000;
	name = last[(int)rand%41] +
	firstName[(int)rand%101];
	//System.out.println("In ScrambleName Method, ScrambleServiceImpl. random*100000 = " + rand);
	//27376.37402766815
	
	return name; }

	String scramblePhoneNum(String str) 
	{ 
		String phoneNum = "010-";
		double rand = Math.random()*10000;
		String randStr = String.valueOf((int)rand);
		//randStr.substring(0, 4); -> padding
		randStr=padding("0",randStr,4);
		phoneNum = phoneNum + randStr + "-";
		
		rand = Math.random()*10000;
		randStr = String.valueOf((int)rand);
		//randStr.substring(0, 4);
		randStr=padding("0",randStr,4);
		phoneNum = phoneNum + randStr;
		//System.out.println("Fake PhoneNum : " + phoneNum);
		return phoneNum;
		
		//padding 써서 갱신 필요함
	}

	String scrambleRrn(String str) 
	{ 
		String rrn;
//		1. year month day gender 랜덤
		int year = randomYear();
		int month = randomMonth();
		int day = randomDay();
		int gender = gender(year);
		rrn = mixFront(year,month,day)+"-"+gender;
		//System.out.println("year : " + year + "\n" + "month : " + month + "\n" + "day : " + day + "\n"
		//		+ "gender : " + gender);
		
//		2. date형식 맞춰주기(앞자리)
//		0~5, 6번 gender
//		3. 7~11까지 랜덤숫자 하나씩 끌고 옴
		int[] arr = new int[5];
		for(int i = 0; i<5;i++) {
			arr[i] = (int)(Math.random()*10);
			rrn += arr[i];
			
			//System.out.println("arr[" + i + "] : " + arr[i]);
		}
		int[] total = {year%100/10,year%1000,month/10,month%10,day/10,day%10,
				gender,arr[0],arr[1],arr[2],arr[3],arr[4]};
		int last = lastNumber(total);
		rrn+=last;

		//System.out.println("last : " + last);
		// Padding하고 다시 만들어야함
//		4. 12번쨰 마지막 자리 수는 인증용도니까 맞춰줌.
//		5. 모든 걸 하나로 통합시켜줌!
//		끝!
		//System.out.println("Fake RRN : " + rrn + "\n length : " + rrn.length());
//		
		return rrn;
	}
	
	@Override
	public String scrambleAccount(String bank_code) throws Exception {
		int length = 11;
		String res;
		
		int randOne = (int)(Math.random()*10000000);
		int randTwo = (int)(Math.random()*10000000);
		

		//System.out.println("In ScrambleAccount, RandOne : " + randOne);
		//System.out.println("In ScrambleAccount, RandTwo : " + randTwo);
		
		res = padding(String.valueOf(randOne),String.valueOf(randTwo),length);
		int randThree = (int)(Math.random()*10000000);
		res = padding(String.valueOf(randThree),res,length);
		//System.out.println("In ScrambleAccount, RandThree : " + randThree);
		
		//System.out.println("In ScrambleService, scrambleAccount, FakeAccount : " + res);
		
		res = res + bank_code;
		
		return res;
	}
	
	int randomYear(){
		int year = 2001;
		// 20세 이상부터로 잡자, 2001! 
		// range = max - min + 1 = year - 1920 + 1 = year - 1919
		return (int)(Math.random()*(year-1919))+1920;
		// (Math.random() * range) + min
		// javascript의 random과 java의 random에 차이가 있으면 문제 생김
	}
	
	int randomMonth() {
		int max = 12;
		return (int)(Math.random()*max)+1;
	}
	
	int randomDay() {
		int max = 31;
		return (int)(Math.random()*max)+1;
	}
	// padding 제작후 Month Day 최소값을 1로 줄여야함
	
	String mixFront(int y, int m, int d) {
		String year = String.valueOf(y);
		String month = String.valueOf(m);
		String day = String.valueOf(d);
		
		month = padding("0",month,2);
		day = padding("0",day,2);
		
		String res = year.substring(2, 4)+month+day;
		
		// 결국 padding은 만들어야 했다.. Month Day가 한자리면 0채워줘야함.
		// 최종형태 -> ju.push((''+year+pad(month,'0',2)+pad(day,'0',2)).substring(2)); 
		
		return res;
	}
	
	int gender(int y) {
		if (y >= 2000) 
			return (int)(Math.random() * 2) + 3;
		else 
			return (int)(Math.random() * 2) + 1;
	}
	// 여기에 randomYear를 통해 나온 year를 집어넣어야 함
	
	int lastNumber(int[] arr) {
		int num = 11-
				((arr[0]*2+arr[1]*3+arr[2]*4+arr[3]*5+arr[4]*6+arr[5]*7
						+arr[6]*8+arr[7]*9+arr[8]*2+arr[9]*3+arr[10]*4+arr[11]*5)%11);
		//System.out.println("For Fake RRN, Last Number's BEFORE : " + num);
		return num<9 ? num : num %10;
	}
	
	String padding(String front, String back, int length) {
		// 합이 n개가 되도록 하고, 안되면 앞에 무엇을 혹은 뒤에 무엇을 넣어준다.
		for(int i = length; i>0;i--) {
			back = front + back;
		}		
		return back.substring(back.length()-length,back.length());
	}
	
	String scrambleStore() {
		String[] stores = {"GS25", "세븐일레븐", "CU", "미니스톱", "떡볶이집", "한입초밥", "정호보쌈족발", "여우곱창", "작은집", "베니스", 
				 "베르데", "요거프레소", "별빛맥주", "곱창전골", "신용카드지급", "세상맛집,OPO", "하른감자탕", 
				 "신정은", "빽다방", "바르다김선생", "코다차야", "더식당", "김정석", "홍콩반점", "보드게임카페", "파리바게트", "정명숙",
				 "천희양꼬치", "엔제리너스커피", "평촌태평양약국", "김정석", "공가네", "미스터피자", "베스코", "민트치과", "깐부치킨", "와라와라",
				 "못난이아구나라", "김밥천국", "이니스프리", "가장맛있는족발", "호텔쁠랑", "소호복집", "깐부치킨", "서원족발", "이지석", "여래"};
		return stores[(int)(Math.random()*1000)%47];
	}
	
	public Date scrambleDate() {
		String[] dateList = {"2020-01-03 21:23","2020-01-04 21:23","2020-01-05 21:23","2020-01-06 21:23","2020-01-07 21:23"
				,"2020-01-08 21:23","2020-01-09 21:23","2020-01-10 21:23","2020-01-011 21:23","2020-01-12 21:23",
				"2020-02-01 13:23","2020-02-10 23:23","2020-03-01 22:22","2020-03-02 22:22","2020-04-05 12:22",
				"2020-04-06 12:22","2020-04-08 12:22","2020-05-02 12:22","2020-05-03 12:22","2020-05-04 12:22"};
			
		return StringToDate(dateList[(int)(Math.random()*1000)%19]);
	}
	
	@SuppressWarnings("deprecation")
	Date StringToDate(String date) {
		int year = Integer.parseInt(date.substring(0, 4));
		int month = Integer.parseInt(date.substring(5,7));
		int day = Integer.parseInt(date.substring(8,10));
		int hour = Integer.parseInt(date.substring(11, 13));
		int min = Integer.parseInt(date.substring(14,16));
		Date dateE = new Date(year-1900,month-1,day,hour,min,0);

		return dateE;
	}

	
	public ApiVO createBoard () {
		ApiVO vo = new ApiVO();
		/*
		 * 1. 입금인지 출금인지
		 * 2. 얼마를 사용했는지
		 * 3. Store는 어디인지
		 */
		
		if ((int)(Math.random()*100) % 2 == 1) {
			vo.setBank_deposit("입금");	
			vo.setBank_amount((int)(Math.random()*1000)%100 * 1000);
			vo.setBank_store(scrambleName("정해진사람이름아냐내부은행에있는사람도아니야아직아냐"));
			return vo;
		}
		else {
			vo.setBank_deposit("출금");
			vo.setBank_amount((int)(Math.random()*10000)%10 * 10000);
			vo.setBank_store(scrambleStore());
			return vo;
		}
		
	}
}