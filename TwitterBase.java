import java.io.*;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.conf.Configuration;


public abstract class TwitterBase{
	// Provides the base template for any Twitter app - other apps must extend this in order to be functional

	// Twitter stuff
	protected TwitterFactory factory;
	protected Twitter twitter;
	protected ConfigurationBuilder builder;

	// Credentials
	private boolean credentials = false;
	private String consumer_key;
	private String consumer_secret;
	private String access_token;
	private String access_token_secret;

	public TwitterBase(String credentials){
		builder = new ConfigurationBuilder();
		readCredentials(credentials);
	}

	protected void init(){
		// Initializes the twitter app, connects to Twitter, etc.
		if (credentials){
            System.err.println("Trying to connect to twitter...");
            Configuration configuration = builder.build();
            factory = new TwitterFactory(configuration);
            twitter = factory.getInstance();
            System.err.println("Connected to Twitter.");		
		
		
		
		
		}
		else{
			System.err.println("Credentials not loaded. Exiting.");
			System.exit(1);
		}
	}
	// Private helper methods
	protected void readCredentials(String cred){
		try{
			BufferedReader br = new BufferedReader(new FileReader(cred));
			// We need to read four lines, in order, no more, no less
			// We should probably make this more user friendly for release - but whatever
			consumer_key = br.readLine();
			consumer_secret = br.readLine();
			access_token = br.readLine();
			access_token_secret = br.readLine();
			br.close();
			
			// Actually set the credentials now
			builder.setOAuthConsumerKey(consumer_key);
            builder.setOAuthConsumerSecret(consumer_secret);
            builder.setOAuthAccessToken(access_token);
            builder.setOAuthAccessTokenSecret(access_token_secret);

			
			credentials = true;
		}
		catch(IOException ioex){
			System.err.println("Could not load credentials file " + cred);
			ioex.printStackTrace();
		}
	}

	
	protected void tweet(String msg){
		// Writes the following message to Twitter
		if (twitter != null){
			try{
				Status status = twitter.updateStatus(msg);
			}
			catch (Exception tex){
				System.err.println("Error posting to Twitter.");
				tex.printStackTrace();
			}
		}
		else{
			System.err.println("Twitter connection not established. Exiting.");
			System.exit(1);
		}
	}	
	
	// To be overwritten - this is how we do it
	protected abstract void process();


}
