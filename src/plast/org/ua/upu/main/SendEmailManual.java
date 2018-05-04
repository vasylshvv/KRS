package plast.org.ua.upu.main;

import java.util.Date;

public class SendEmailManual {
	public static void main(String[] args) throws Exception {
		
		Date datestart = new Date();
		long start = datestart.getTime();
		SendCodeKurinUPU scku = new SendCodeKurinUPU();
		Long idkurin = (long) 76; 
		scku.codeKurin(idkurin);

//		SendCodeZvyazkovyy sczv = new SendCodeZvyazkovyy();
//		Long idzv = (long)26;
//		sczv.codeZvyazkovvy(idzv);
		
		
//		Long idsmhurtok = (long)52;
//		
//		SendCodeSMHurtokUPU smc = new SendCodeSMHurtokUPU();
//		//Long idsmhurtok = (long) 2;
//		smc.codeSMHurtok(idsmhurtok);
//		
//
//		
//		SendCodeSMVporyadnyk scsmv = new SendCodeSMVporyadnyk();
//		//Long idsmhurtok = (long)2;
//		scsmv.codeVporyadnyk(idsmhurtok);
		
		Date dateend = new Date();
		
		long end = dateend.getTime();
		
		Long counttime = (end-start)/1000;
		System.out.println("counttime = "+counttime);
	}
}
