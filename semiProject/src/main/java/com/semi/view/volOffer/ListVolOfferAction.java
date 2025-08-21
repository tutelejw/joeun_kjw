package com.semi.view.volOffer;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.semi.domain.VolOffer;
import com.semi.framework.Action;
import com.semi.service.VolOfferService;

public class ListVolOfferAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // --- 로그 추가 ---
        System.out.println("✅ [START] ListVolOfferAction.execute() 메소드가 시작되었습니다.");

        try {
            VolOfferService volOfferService = new VolOfferService();
            // Service 클래스의 메소드를 호출합니다.
            List<VolOffer> volOfferList = volOfferService.getVolOfferList();

            // --- 로그 추가 ---
            // Service로부터 반환받은 데이터의 크기를 출력하여, 데이터를 제대로 받아왔는지 확인합니다.
            if (volOfferList != null) {
                System.out.println("➡️ ListVolOfferAction: Service로부터 " + volOfferList.size() + "개의 데이터를 받았습니다.");
            } else {
                System.out.println("⚠️ ListVolOfferAction: Service로부터 받은 데이터가 null입니다.");
            }

            request.setAttribute("volOfferList", volOfferList);

            // --- 로그 추가 ---
            System.out.println("✅ [END] ListVolOfferAction.execute(): /volOfferList.jsp 로 포워딩합니다.");

            return "../volOfferList.jsp";

        } catch (Exception e) {
            // --- 예외 처리 로그 추가 ---
            // 만약 실행 도중 예외가 발생하면, 어떤 오류인지 콘솔에 출력합니다.
            System.err.println("❌ ListVolOfferAction.execute() 도중 오류 발생!");
            e.printStackTrace();
            // 예외 발생 시 에러 페이지로 이동하거나, 다른 처리를 할 수 있습니다.
            // return "/error.jsp";
            throw e; // 예외를 다시 던져서 서버가 처리하도록 할 수도 있습니다.
        }
    }
}