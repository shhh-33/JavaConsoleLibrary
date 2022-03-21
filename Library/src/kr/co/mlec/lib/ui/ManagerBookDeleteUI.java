package kr.co.mlec.lib.ui;

import kr.co.mlec.lib.dao.ManagerBookDAO;
import kr.co.mlec.lib.dao.BCheckDAO;


public class ManagerBookDeleteUI extends BaseUI{
   
   

      @Override
      public void execute() {
         
          String book_n = scanStr("삭제할 책의 책번호를 입력하세요 : ");
   
         
          ManagerBookDAO bDAO =new ManagerBookDAO();
          
          BCheckDAO bnumDAO = new BCheckDAO(); // 책번호 확인
          int resultBnum = bnumDAO.checkBNum2(book_n);
          
         bDAO.deleteBook(book_n);
          
         System.out.println("도서정보가 삭제되었습니다.");
         
      }
   

}
