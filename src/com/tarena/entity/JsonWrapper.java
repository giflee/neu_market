package com.tarena.entity;

import net.sf.json.JSONObject;

public class JsonWrapper {

	private JSONObject jsonObject;
	
	private JsonWrapper(JSONObject jsonObject){
		this.jsonObject=jsonObject;
	}
	
	private JSONObject getJsonObject(){
		return jsonObject;
	}
	
	public static class Builder{
		
		private JSONObject jo;
		
		public Builder(){
			this.jo=new JSONObject();
		}
		public Builder(boolean state,String code,String message,JSONObject content){
			this.jo=new JSONObject();
			jo.put("REQUEST_SUCCESS", state?"1":"0");
			jo.put("ERROR_CODE", code==null?"":code);
			jo.put("RESULT_MESSAGE", message==null?"":message);
			jo.put("RESULT_CONTENT", content==null?"":content);
		}
		
		public Builder setRequestState(boolean state){
			this.jo.put("REQUEST_SUCCESS", state?"1":"0");
			return this;
		}
		
		public Builder setErrorCode(String code){
			this.jo.put("ERROR_CODE", code==null?"":code);
			return this;
		}
		
		public Builder setResultMessage(String message){
			this.jo.put("RESULT_MESSAGE", message==null?"":message);
			return this;
		}
		
		public Builder setResultContent(JSONObject json){
			this.jo.put("RESULT_CONTENT", json==null?"":json);
			return this;
		}
		
		public JSONObject build(){
			return new JsonWrapper(this.jo).getJsonObject();
		}
		
	}
}
