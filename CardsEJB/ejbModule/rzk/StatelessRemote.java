package rzk;

import javax.ejb.Remote;

@Remote
public interface StatelessRemote {
	public void sendQueueMessage(String cardFrom,String cardTo, String email, String type);
	public void sendTopicMessage(String cardFrom,String cardTo, String email, String type);
}
