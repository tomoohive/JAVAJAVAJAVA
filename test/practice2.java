public class practice2{
	private String data;

	public myTest(String data){
		this.data = data;
	}
	public void show(){
		System.out.printf(" data=%s\n",data);
	}
	public static void main(String[] args){
		myTest myData("Apple-Pen");
		myData.show();
	}
}
