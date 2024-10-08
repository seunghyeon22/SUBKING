




const IMP = window.IMP;
IMP.init('imp50777305'); // 본인 가맹점 식별코드로 변경


function requestPay() {
       IMP.request_pay({
           pg: 'kakaopay', // PG사 선택 (예: KG이니시스)
           pay_method: 'card', // 결제 수단 (예: 카드)
           merchant_uid: 'merchant_' + new Date().getTime(), // 고유한 주문번호 생성
           name: '결제테스트', // 상품명
           amount: 14000, // 결제 금액
           buyer_email: 'iamport@siot.do', // 구매자 이메일
           buyer_name : '테스트', // 구매자 이름
		   buyer_tel : '010-1234-1234', // 구매자 전화번호
		   buyer_addr : '부산광역시 진구 부전동' // 구매자 주소
		     // ... 기타 필요한 파라미터 추가
       }, function (rsp) {
           if (rsp.success) {
               // 결제 성공 처리
               console.log('결제 성공:', rsp);
			   window.location.href = "http://localhost:8080/240930subKingProject/custom/custom";
			   		
           } else {
               // 결제 실패 처리
               console.log('결제 실패:', rsp);
           }
       });
   }


//
//const axios = require('axios');
//
//const getAccessToken = () => {
//    try {
//        const response =  axios.post('https://api.iamport.kr/users/getToken', {
//            imp_key: '1363852651607640',
//            imp_secret: 'fb493bdc58fe01c79e12934fa674ec55371a7788e4f165c48087eaa33b6804361c7d5094515b4a0d',
//        });
//        const { access_token } = response.data.response;
//        console.log('Access Token:', access_token);
//        return access_token;
//    } catch (error) {
//        console.error('Error getting access token:', error.message);
//    }
//};
//
//// 호출 예시
//const accessToken = getAccessToken();