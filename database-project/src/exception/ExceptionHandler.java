package exception;


public class ExceptionHandler {
	public static class MyError {
		int errno;
		String message;

		public MyError(int errno, String message) {
			this.errno = errno;
			this.message = message;
		}

		public int getErrno() {
			return errno;
		}
		public String getMessage(){
			return message;
		}
		
		public static final int NULL_ERROR = 0; //违反了非空约束
		public static final int FOREIGN_KRY_CONSTRAINT = 1;//违反外键约束
		public static final int DUP_KEY_ERROR = 2;//违反了唯一约束
		public static final int VALUE_TOOLONG = 3;//值太长
		public static final int MANAGER_NOT_EXIST = 4;
		public static final int DATE_FORMAT_ERROR = 5;
		public static final int INT_INVALID = 6;
		public static final int OWNER_NOT_EXIST = 7;
		public static final int OTHER_ERROR = 8;//其他错误
		
		public static final MyError errormap[]=
			{
			  new MyError(NULL_ERROR,"Input value cannot be null."),
			  new MyError(FOREIGN_KRY_CONSTRAINT,"doesn't exist."),
			  new MyError(DUP_KEY_ERROR," has already existed."),
			  new MyError(VALUE_TOOLONG,"The input value is too long."),
			  new MyError(MANAGER_NOT_EXIST,"Manager does not exist."),
			  new MyError(DATE_FORMAT_ERROR,"The input format of date is incorrect."),
			  new MyError(INT_INVALID,"Please enter an integer."),
			  new MyError(OWNER_NOT_EXIST,"OwnerNo does not exist."),
			  new MyError(OTHER_ERROR,"Other error.")
			};
		
		public static String getError(int errno){
					return errormap[errno].getMessage();		
		}
		
		public static String getErrorInfo(String msg){
			if(msg.indexOf("违反了非空约束")!=-1)
				return getError(NULL_ERROR);
			else if(msg.indexOf("违反外键约束")!=-1)
			{   
				String[] str=msg.split("\\_");
				return str[1]+" "+getError(FOREIGN_KRY_CONSTRAINT);
			}
			else if(msg.indexOf("违反唯一约束")!=-1)
			{
				String[] str=msg.split("\\(|\\)");
				return str[1]+"("+str[3]+")"+getError(DUP_KEY_ERROR);
			}	
			else if(msg.indexOf("值太长")!=-1)
				return getError(VALUE_TOOLONG);
			else if(msg.indexOf("manager does not exist")!=-1)
				return getError(MANAGER_NOT_EXIST);
			else if(msg.indexOf("无效的类型 date 输入语法")!=-1)
				return getError(DATE_FORMAT_ERROR);
			else if(msg.indexOf("无效的整数类型输入语法")!=-1)
				return getError(INT_INVALID);
			else if(msg.indexOf("Owner does not exist")!=-1)
				return getError(OWNER_NOT_EXIST);
			else if(msg.indexOf("查询没有传回任何结果")!=-1)
				return "";
			else
				return getError(OTHER_ERROR);
		}
	}
}
