package jw.service.user.vo;

//사용자 정보를 담는 클래스 (Value Object)
public class UserVo {
 private String id;
 private String pwd;

 // 생성자
 public UserVo(String id, String pwd) {
     this.id = id;
     this.pwd = pwd;
 }

 // getter, setter
 public String getId() {
     return id;
 }

 public String getPwd() {
     return pwd;
 }
}