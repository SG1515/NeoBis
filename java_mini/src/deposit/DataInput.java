package deposit;

import java.io.*;

public class DataInput {
	   private static BufferedReader br;

	    private DataInput() {
	        // private 생성자로 인스턴스화 방지
	    }

	    public static BufferedReader getInstance() {
	        if (br == null) {
	            br = new BufferedReader(new InputStreamReader(System.in));
	        }
	        return br;
	    }

	    public static String readLine() throws IOException {
	        return getInstance().readLine();
	    }

	    public static void close() {
	        if (br != null) {
	            try {
	                br.close();
	            } catch (IOException e) {
	                System.err.println("Error closing BufferedReader: " + e.getMessage());
	            }
	        }
	    }

}
