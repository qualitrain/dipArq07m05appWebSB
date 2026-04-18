public class Test { public static void main(String[] args) { io.jsonwebtoken.Jws<io.jsonwebtoken.Claims> j = null; System.out.println(j.getDigest()); System.out.println(j.getPayload()); } }
