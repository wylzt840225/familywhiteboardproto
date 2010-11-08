package jonstewardappreciation.cs160.berkeley.edu;

public class Comment {
	String name;
	String comment;
	int priority;
	Comment next;
	
	public Comment(String name, String comment, int priority)
	{
		this.name = name;
		this.comment = comment;
		this.priority = priority;
		this.next = null;
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
	
	public void linkComment(Comment c)
	{
		if (this.next != null)
		{
			this.next.linkComment(c);
		}
		else
		{
			this.next = c;
		}
	}
}
