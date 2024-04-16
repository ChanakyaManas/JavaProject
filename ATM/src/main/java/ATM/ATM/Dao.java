package ATM.ATM;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Dao {
Scanner in=new Scanner(System.in);
	@Autowired
	private JdbcTemplate jt;
		
	@CrossOrigin(origins = "http://localhost:3000")
	@RequestMapping("bank/{pin}")
	public ResponseEntity<Object> Bank(@RequestBody request r, @PathVariable int pin) {
	    Accountdata a = r.getAt();
	    Transactions t = r.getT();
	    System.out.println("1.Generate Pin");
	    System.out.println("2.Withdrawl");
	    System.out.println("3.Deposit");
	    System.out.println("4.Check Balance");
	    System.out.println("5.Print Statement");
	    int choice = in.nextInt();

	    if (choice == 1) {
	        System.out.println("Enter Mno");
	        String mno = in.next();
	        try {
	            String mon = "select mno from bank.acdata where mno =?";
	            String so = jt.queryForObject(mon, String.class, mno);
	           System.out.println(so);
	            if (mno.equals(so)) {
	            
	            	mon = "select acid from bank.acdata where mno=?";
	                int s = jt.queryForObject(mon, Integer.class, so);
	             
	                
	                double rand = Math.random();
	                System.out.println(rand);
	                int pot = (int) (rand * 10000);
	            
	                mon = "update bank.acdata set rand=? where acid=?";
	                int po = jt.update(mon, pot, s);

	                mon = "select rand from bank.acdata where acid=?";
	                po = jt.queryForObject(mon, Integer.class, s);
	               System.out.println("OTP GENERATED SUCCESSFULLY");
	                System.out.println("Enter The OTP");
	                pot = in.nextInt();
	                if (po == pot) {
	                	System.out.println("Enter The Pin");
	                	pin=in.nextInt();
	                	System.out.println("UPDATD :"+pin);
	                	mon = "update bank.acdata set pin=? where acid=? ";
	                    jt.update(mon, pin, s);
	                }

	            } else {
	                System.out.println("MNO NOT EXIST");
	            }
	        } catch (EmptyResultDataAccessException e) {
	            System.out.println("MNO NOT EXIST "+e);
	        }

	    }
	    else if(choice==2) {
	    	String qr = "SELECT balance FROM bank.acdata WHERE pin = ?";
	    	Double bal=jt.queryForObject(qr, Double.class,pin);
	        
	    	String query = "SELECT acid FROM bank.acdata WHERE pin = ?";
	        Integer dbPin = jt.queryForObject(query, Integer.class, pin);
	     
	        
	        if(bal>t.getWithdrawl()) {	
	        	Double newBalance=bal-t.getWithdrawl();
	        	String query1 = "INSERT INTO bank.Transactions (withdrawl, balance, fkacid) VALUES (?, ?, ?)";
	            jt.update(query1,t.getWithdrawl(),newBalance, dbPin);
	            qr="update bank.acdata set balance=? where acid=?";
	            jt.update(qr,newBalance,dbPin);
	        System.out.println("Withdrawel Amount"+t.getWithdrawl());
	        System.out.println("Balance "+newBalance);
	        }
	        	else {
	        	System.out.println("Insuficient Funds");
	        }
	     
	    }
	    else if(choice==3) {
	    	String qr="select balance from bank.acdata where pin=?";
	    	Double bal=jt.queryForObject(qr, Double.class,pin);
	    	qr="select acid from bank.acdata where pin=?";
	    	Integer dbpin=jt.queryForObject(qr, Integer.class,pin);
	    	Double B=bal+t.getDeposit();
	    	qr="insert into bank.transactions(deposit,balance,fkacid)values(?,?,?)";
	    	jt.update(qr,t.getDeposit(),B,dbpin);
	    	qr="update bank.acdata set balance=? where acid=?";
            jt.update(qr,B,dbpin);
	    	System.out.println("Deposited :"+t.getDeposit());
	    	System.out.println("Balance"+B);
	    }
	    else if(choice==4) {
	    	String qr = "SELECT balance FROM bank.acdata WHERE pin = ?";
	    	Double bal=jt.queryForObject(qr, Double.class,pin);
	    	System.out.println("Current Balance"+bal);
	    	  }
	    else if(choice==5) {
	    String qr="select acid from bank.acdata where pin=?";	
	    Integer id=jt.queryForObject(qr, Integer.class,pin);
	    int no=in.nextInt();
	    qr="select * from bank.transactions where fkacid=? order by tno desc limit ";
	    
	   List<Transactions>l= jt.query(qr, new BeanPropertyRowMapper<>(Transactions.class),id);
	    for (Transactions transactions : l) {
			System.out.println(transactions);
		}
	    }

	    return new ResponseEntity<>(HttpStatus.ACCEPTED);

	}

	
}
