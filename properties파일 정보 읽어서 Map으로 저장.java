private Map<String, Action> boardMap;
	
	
	//BoardController 처음 접속시 딱 한 번만 처리된다.
	@Override
	public void init(){
		boardMap=new Hashtable<String, Action>();
		//properties파일에서 데이터 읽어서 Map<String, Action> 으로 변환 저장
		//mybatis 라이브러리의 Resources객체 이용하면 편리...
		String file="/action/board/board-info.properties";
		try {
			//Properties 파일에서 load해서 Properties 객체로 읽어들인다.
			Properties prop=Resources.getResourceAsProperties(file);
			//key만 먼저 set으로 변경
			Set<Object> sets=prop.keySet();
			
			for(Object obj:sets) {
				//Map에 저장할 key
				String key=(String)obj;
				//set에 저장된 key를 이용해서 value를 읽는다.
				String className=(String) prop.get(key);
				//Class.forName(className)를 이용해서
				//문자열로 된 클래스이름을 객체화 시키자
				try {
					//Class<?> clazz=Class.forName(className);
					//clazz.getDeclaredConstructor().newInstance();
					Action action=(Action)Class.forName(className).getDeclaredConstructor().newInstance();
					//key, action을 Map에 저장
					boardMap.put(key, action);
				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
				}
				
			}
				
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}