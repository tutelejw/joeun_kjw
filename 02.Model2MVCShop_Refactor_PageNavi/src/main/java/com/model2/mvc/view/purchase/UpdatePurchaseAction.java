package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.domain.Purchase;

public class UpdatePurchaseAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 트랜잭션 번호를 파라미터에서 받아옴
        int tranNo = Integer.parseInt(request.getParameter("tranNo"));
        System.out.println("Received transaction number: " + tranNo);  // 디버깅용 출력

        // Purchase 객체 생성
        Purchase vo = new Purchase();
        vo.setTranNo(tranNo);

        // 나머지 입력 값들 받아오기 (구매자 정보)
        String receiverName = request.getParameter("receiverName");
        String receiverPhone = request.getParameter("receiverPhone");
        String divyAddr = request.getParameter("receiverAddr");
        String divyRequest = request.getParameter("receiverRequest");
        String divyDate = request.getParameter("divyDate");

        // 디버깅 출력 추가
        System.out.println("Received receiverName: " + receiverName);
        System.out.println("Received receiverPhone: " + receiverPhone);
        System.out.println("Received receiverAddr: " + divyAddr);
        System.out.println("Received receiverRequest: " + divyRequest);
        System.out.println("Received divyDate: " + divyDate);

        // 입력 값들 Purchase에 설정
        vo.setReceiverName(receiverName);
        vo.setReceiverPhone(receiverPhone);
        vo.setDivyAddr(divyAddr);
        vo.setDivyRequest(divyRequest);
        vo.setDivyDate(divyDate);
        
        // 날짜 파싱 및 디버깅
        System.out.println("Parsed delivery date: " + divyDate);  // 디버깅용 출력

        // PurchaseService 객체 생성 및 업데이트 처리
        PurchaseService service = new PurchaseServiceImpl();
        System.out.println("Updating purchase information...");  // 디버깅용 출력
        service.updatePurchase(vo);

        // 세션에서 tranNo에 해당하는 구매정보를 업데이트
        HttpSession session = request.getSession();
        Purchase sessionPurchase = (Purchase) session.getAttribute("vo");

        // 세션에 Purchase 객체가 없으면 새로 저장
        if (sessionPurchase == null) {
            System.out.println("No Purchase found in session, adding new object.");
            session.setAttribute("vo", vo);  // 세션에 Purchase 객체 저장
        } else {
            System.out.println("Found existing Purchase in session.");
        }

        // 세션에서 tranNo에 해당하는 구매정보를 갱신
        int sessionId = sessionPurchase != null ? sessionPurchase.getTranNo() : -1;
        System.out.println("Session transaction number: " + sessionId);  // 디버깅용 출력

        // tranNo가 일치하면 세션의 구매정보를 갱신
        if (sessionId == tranNo) {
            session.setAttribute("vo", vo); // 세션에 업데이트된 Purchase 저장
            System.out.println("Purchase information updated in session.");  // 디버깅용 출력
        } else {
            System.err.println("Transaction number mismatch between session and request.");  // 에러 메시지 출력
        }

        // 구매 수정 후, 해당 거래 상세보기 페이지로 리다이렉트
        System.out.println("Redirecting to purchase details page for tranNo: " + tranNo);  // 디버깅용 출력
        return "redirect:/getPurchase.do?tranNo=" + tranNo;
    }
}
