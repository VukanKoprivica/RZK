package rzk;

import javax.ejb.Remote;

@Remote
public interface SenderSLRemote {
	public void sendQueueMessage();
	public void sendTopicMessage();
}
