package test;

public class main {
	public main() {
		// TODO Auto-generated constructor stub

		
		char[] buffer = new char[32];
		int i;
		for (i = 0; i < 8; i++) {
			buffer[i] = password.charAt(i);
		}
		for (; i < 16; i++) {
			buffer[i] = password.charAt(23 - i);
		}
		for (; i < 32; i += 2) {
			buffer[i] = password.charAt(46 - i);
		}
		for (i = 31; i >= 17; i -= 2) {
			buffer[i] = password.charAt(i);
		}
		String s = new String(buffer);
		s.equals("jU5t_a_sna_3lpm18g947_u_4_m9r54f");
		
		String a = "jU5t_a_sna_3lpm18g947_u_4_m9r54f";
		String res;
		
		for (i = 0; i < 8; i++) {
			res[i] = a[i];
		}
		
	}

}
