
public class Queue 
{

	public Shot[] queueArray;
	public int front;
	public int rear;
	private int size;
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	private int count;
	private int path;
	public int sayac;

	public Queue()
	{
		size=4;
		queueArray=new Shot[size];
		front=0;
		rear=size-1;
		count=0;
	}
	public Queue(int newSize)
	{
		if(newSize<=0)
			size=10;
		else
			size=newSize;

		queueArray=new Shot[size];
		front=0;
		rear=size-1;
		count=0;
	}
	public Queue(int newSize,int p)
	{
		if(newSize<=0)
			size=10;
		else
			size=newSize;

		queueArray=new Shot[size];
		front=0;
		rear=size-1;
		count=0;
		path=p;
	}
	public void initializeQueue()
	{
		front=0;
		rear=size-1;
		count=0;
	}
	public boolean isEmpty()
	{
		return(count==0);
	}
	public boolean isFull()
	{
		return(count==size);
	}
	public Shot getFront()
	{
		assert(!isEmpty());
		return queueArray[front];
	}
	public Shot getRear()
	{
		assert(!isEmpty());
		{
			return queueArray[rear];
		}
	}
	public void addQueue(Shot newItem,int p)
	{
		if(!isFull())
		{
			path=p;
			rear=(rear+1)%size;
			count++;
			queueArray[rear]=newItem;
		}
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void deleteQueue()
	{
		if(!isEmpty()) {
			count--;
			front=(front+1)%size;
		}
		else
			System.out.println("Queue is empty");
	}
	public void outputQueue()
	{	
		int counter=count;
		if(count!=0)
		{
			for (int i = front; i <= size-1; i++)
			{
				if(counter!=0)
				{

					System.out.println(queueArray[i]);
					counter--;


				}
			}
			for (int i = 0; i <= rear; i++) 
			{
				if(counter!=0)
				{

					System.out.println(queueArray[i]);
					counter--;

				}
			}
		} 
		else
			System.out.println("Empty");
	}
	public void outputQueueTers()
	{	
		int counter=count;
		if(count!=0)
		{
			for (int i = 0; i <= size-1; i++)
			{
				if(counter!=0)
				{
					if(queueArray[i]!=null)
					{
						System.out.println(queueArray[i]);
						counter--;
					}

				}
			}
		} 
		else
			System.out.println("Empty");
	}

}

