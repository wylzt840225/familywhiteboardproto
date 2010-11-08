package jonstewardappreciation.cs160.berkeley.edu;

public class Comment {
	String name;
	String comment;
	int priority;
	
	public Comment(String name, String comment, int priority)
	{
		this.name = name;
		this.comment = comment;
		this.priority = priority;
	}
	
	public String getName()
	{
		return name;
	}

	public String getComment()
	{
		return comment;
	}
	
	public int getPriority()
	{
		return priority;
	}
}
