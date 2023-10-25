package code;
import java.util.*;


import junit.extensions.RepeatedTest;
public class Matrix {
	
	public static int gridSize;
	public static int loc;
	public static int hostageCount;
	public static int hostageDamage;
	public static int MaxhostageCarry;
	public static int pillCount;
	public static int NeoX;
	public static int NeoY;
	public static int TelephoneX;
	public static int TelephoneY;
	public static int emptyCells; // 2 for Neo and Telephone Booth
	public static int padCount;
	public static int MaxAgentCount;
	public static int AgentCount;
	public static String[][] grid;
	public static boolean[][] gridBool;
	public static String gridString="";
	public static String HostageString="";
	public static String AgentString="";
	public static String PillString="";
	public static String PadString="";
	public static String PadString2="";
	public static int pairCount = 1;
	
//	public static ArrayList<String> Operators;
	public static HashSet<String> RepeatedNodes= new HashSet<String>();
	
	
	public static String plan="";
	public static int noOfExpandedNodes = 0;
	public static int deaths = 0;
	public static int killss = 0;
	
	public static Random rand = new Random();


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		genGrid();
		
		//HOSTAGE CARRY HANDLE IN ILLEGAL OPS
		
//		String grid0 = "3,3;2;1,2;2,2;;;;1,2,10;";
		
//		String grid0 = "3,3;2;0,0;2,0;;0,2,2,1;0,1,1,2;2,2,0;";
		
		String grid0 = "5,5;2;3,4;1,2;0,3,1,4;2,3;4,4,0,2,0,2,4,4;2,2,91,2,4,62";
		String grid1 = "5,5;1;1,4;1,0;0,4;0,0,2,2;3,4,4,2,4,2,3,4;0,2,32,0,1,38";
		String grid2 = "5,5;2;3,2;0,1;4,1;0,3;1,2,4,2,4,2,1,2,0,4,3,0,3,0,0,4;1,1,77,3,4,34";
		String grid3 = "5,5;1;0,4;4,4;0,3,1,4,2,1,3,0,4,1;4,0;2,4,3,4,3,4,2,4;0,2,98,1,2,98,2,2,98,3,2,98,4,2,98,2,0,1";
		String grid4 = "5,5;1;0,4;4,4;0,3,1,4,2,1,3,0,4,1;4,0;2,4,3,4,3,4,2,4;0,2,98,1,2,98,2,2,98,3,2,98,4,2,98,2,0,98,1,0,98";
		String grid5 = "5,5;2;0,4;3,4;3,1,1,1;2,3;3,0,0,1,0,1,3,0;4,2,54,4,0,85,1,0,43";
		String grid6 = "5,5;2;3,0;4,3;2,1,2,2,3,1,0,0,1,1,4,2,3,3,1,3,0,1;2,4,3,2,3,4,0,4;4,4,4,0,4,0,4,4;1,4,57,2,0,46";
		String grid7 = "5,5;3;1,3;4,0;0,1,3,2,4,3,2,4,0,4;3,4,3,0,4,2;1,4,1,2,1,2,1,4,0,3,1,0,1,0,0,3;4,4,45,3,3,12,0,2,88";
		String grid8 = "5,5;2;4,3;2,1;2,0,0,4,0,3,0,1;3,1,3,2;4,4,3,3,3,3,4,4;4,0,17,1,2,54,0,0,46,4,1,22";
		String grid9 = "5,5;2;0,4;1,4;0,1,1,1,2,1,3,1,3,3,3,4;1,0,2,4;0,3,4,3,4,3,0,3;0,0,30,3,0,80,4,4,80";
		String grid10 = "5,5;4;1,1;4,1;2,4,0,4,3,2,3,0,4,2,0,1,1,3,2,1;4,0,4,4,1,0;2,0,0,2,0,2,2,0;0,0,62,4,3,45,3,3,39,2,3,40";
		
		
	
		String s = solve(grid0, "ID", false);
		System.out.println(s);
//		Node ir = InitialState(grid0);
//		String istate = ir.getNeoState();
//		System.out.println(istate);
//		String removedd = RemoveDamage(istate);
//		System.out.println(removedd);
//		String stt = Move("carry", ir);
//		System.out.println(stt);
//		Node ir2 = new Node(stt);
//		String stt2 = Move("down", ir2);
//		System.out.println(stt2);
//		Node ir3 = new Node(stt2);
//		String stt3 = Move("drop", ir3);
//		System.out.println(stt3);
	}
	
	
	public static String genGrid() {
		//Randomly generate a number from 5-15 to be assigned to the grid size
		gridSize = (int)Math.floor(Math.random()*(15-5+1)+5);
		loc = gridSize -1;
		
		//initialize constant C for hostages to be carried by Neo
		MaxhostageCarry = (int)Math.floor(Math.random()*(4-1+1)+1);
		
		//Generate 2D Array as grid
		grid = new String[gridSize][gridSize];
		
		//Initialize String grid with MxN and C
		gridString=gridString+""+gridSize+","+gridSize+";"+MaxhostageCarry+";";
		
		//Generate 2D Array of Boolean to keep track of empty/occupied cells
		gridBool = new boolean[gridSize][gridSize];
		
		//generate Neo and concatenate his location to the String grid
		String NeoString = genNeo();
		//generate Telephone Booth and concatenate its location to the String grid
		String TeleString = genTele();
		genHost();
		genPill();
		
		emptyCells = (gridSize*gridSize) - (hostageCount + pillCount + 2); // 2 for Neo and Telephone Booth
		
		int maxpads = rand.nextInt(gridSize);
		for(int i = 0; i < maxpads; i ++) {
			genPads();
		}
		
		genAgents();
		
		//concatenate the Strings generated in the above functions for Agents, Pills, Pads, and Hostages in the String grid respectively
		gridString = gridSize + "," + gridSize + ";" + MaxhostageCarry + ";" + NeoString + TeleString + AgentString + PillString + PadString + PadString2 + HostageString;
		
		System.out.println("Grid: " + gridString);
		return gridString;
	}
	
	public static String genNeo() {
		//Randomly generate Neo's location
		NeoX = (int)Math.floor(Math.random()*(loc-0+1)+0);
		NeoY = (int)Math.floor(Math.random()*(loc-0+1)+0);
		
		//Assign Neo to generated location
		grid[NeoX][NeoY]="Neo";
		gridBool[NeoX][NeoY]=true;	
		
		String NeoString = NeoX + "," + NeoY + ";";
		
		return NeoString;
		
	}
		
	public static String genTele() {
		//Randomly generate Telephone Booth's location
		TelephoneX = (int)Math.floor(Math.random()*(loc-0+1)+0);
		TelephoneY = (int)Math.floor(Math.random()*(loc-0+1)+0);
		
		//Make sure no overlapping occurs in generated location (Telephone Booth && Neo)
		while(gridBool[TelephoneX][TelephoneY] == true) {
			TelephoneX = (int)Math.floor(Math.random()*(loc-0+1)+0);
			TelephoneY = (int)Math.floor(Math.random()*(loc-0+1)+0);
		}
		
		//Assign Telephone Booth to generated location
		grid[TelephoneX][TelephoneY] = "Tele"; //Assign Telephone to Location
		gridBool[TelephoneX][TelephoneY]=true;
		
		String TeleString = TelephoneX + "," + TelephoneY + ";";
		
		return TeleString;
		
	
	}
		
	public static void genHost() {
		//Randomly generate a hostage count between 3 and 10
		hostageCount = (int)Math.floor(Math.random()*(10-3+1)+3);
		
		//Randomly generate locations and damages for each generated hostage
		for(int i = 0; i< hostageCount; i++) {
			int hostageX = (int)Math.floor(Math.random()*(loc-0+1)+0);
			int hostageY = (int)Math.floor(Math.random()*(loc-0+1)+0);
			hostageDamage = (int)Math.floor(Math.random()*(99-1+1)+1);
			
			//Make sure no overlapping occurs in generated locations
			while(gridBool[hostageX][hostageY] == true) {
				hostageX = (int)Math.floor(Math.random()*(loc-0+1)+0);
				hostageY = (int)Math.floor(Math.random()*(loc-0+1)+0);
			}
			
			//generate a string to store the location and damage of each generated hostage
			if(i<hostageCount-1) {
				HostageString=HostageString+""+hostageX+","+hostageY+","+hostageDamage+",";
			}
			else {
				HostageString=HostageString+""+hostageX+","+hostageY+","+hostageDamage+";";
			}
			//Assign hostages to generated locations
			gridBool[hostageX][hostageY] = true;
			grid[hostageX][hostageY] = "H " + hostageDamage;
		}
	}
	
	public static void genPill() {
		//Randomly generate a pill count between 1 and the number of hostages
		pillCount = (int)Math.floor(Math.random()*(hostageCount-1+1)+1);
		
		//Randomly generate locations for each generated pill
		for(int i = 0; i< pillCount; i++) {
			int pillX = (int)Math.floor(Math.random()*(loc-0+1)+0);
			int pillY = (int)Math.floor(Math.random()*(loc-0+1)+0);

			//Make sure no overlapping occurs in generated locations
			while(gridBool[pillX][pillY] == true) {
				pillX = (int)Math.floor(Math.random()*(loc-0+1)+0);
				pillY = (int)Math.floor(Math.random()*(loc-0+1)+0);
			}
			
			//generate a string to store the locations of each generated pill
			if(i<pillCount-1) {
				PillString=PillString+""+pillX+","+pillY+",";
			}
			//Assign pills to generated locations
			else {
				PillString=PillString+""+pillX+","+pillY+";";
			}

			gridBool[pillX][pillY] = true;
			grid[pillX][pillY] = "Pill";
		}
	}
	
	public static void genPads() {
			//Randomly generate pairs of pads
		
//			padCount = (int)Math.floor(Math.random()*((((emptyCells/2)-1) -1+1)+1))*2;
			int padX1 = rand.nextInt(gridSize);
			int padY1 = rand.nextInt(gridSize);
			int padX2 = rand.nextInt(gridSize);
			int padY2 = rand.nextInt(gridSize);
//			int padX1 = (int)Math.floor(Math.random()*(loc-0+1)+0);
//			int padY1 = (int)Math.floor(Math.random()*(loc-0+1)+0);
//			int padX2 = (int)Math.floor(Math.random()*(loc-0+1)+0);
//			int padY2 = (int)Math.floor(Math.random()*(loc-0+1)+0);

			
			//Make sure no overlapping occurs in generated locations
			while(gridBool[padX1][padY1] == true) {
				
				padX1 = rand.nextInt(gridSize);
				padY1 = rand.nextInt(gridSize);
				
//				padX1 = (int)Math.floor(Math.random()*(loc-0+1)+0);
//				padY1 = (int)Math.floor(Math.random()*(loc-0+1)+0);
				
				
				
			}

			gridBool[padX1][padY1] = true;
			grid[padX1][padY1] = "Pad" + pairCount + "1";
			while(gridBool[padX2][padY2] == true) {
				padX2 = rand.nextInt(gridSize);
				padY2 = rand.nextInt(gridSize);
//				padX2 = (int)Math.floor(Math.random()*(loc-0+1)+0);
//				padY2 = (int)Math.floor(Math.random()*(loc-0+1)+0);
			}
			gridBool[padX2][padY2] = true;
			grid[padX2][padY2] = "Pad" + pairCount + "2";
			
			PadString=PadString+""+padX1+","+padY1+","+padX2+","+padY2+","+""+padX2+","+padY2+","+padX1+","+padY1+",";
//			if(i<=padCount-2) {
//				PadString=PadString+""+padX1+","+padY1+","+padX2+","+padY2+",";
//				PadString2=PadString2+""+padX2+","+padY2+","+padX1+","+padY1+",";
//			}
//			else {
//				PadString=PadString+""+padX1+","+padY1+","+padX2+","+padY2+";";
//				PadString2=PadString2+""+padX2+","+padY2+","+padX1+","+padY1+";";
//			}

			
			
			pairCount++;	
		
	}
	
	public static void genAgents() {
		//Randomly generate an Agent count between 1 and number of empty cells the grid is finally left with
		MaxAgentCount = emptyCells - padCount;
		AgentCount = (int)Math.floor(Math.random()*(MaxAgentCount-1+1)+1);
		
		//Randomly generate locations for each generated Agent
		for(int i = 0; i< AgentCount; i++) {
			int agentX = (int)Math.floor(Math.random()*(loc-0+1)+0);
			int agentY = (int)Math.floor(Math.random()*(loc-0+1)+0);

			//Make sure no overlapping occurs in generated locations
			while(gridBool[agentX][agentY] == true) {
				agentX = (int)Math.floor(Math.random()*(loc-0+1)+0);
				agentY = (int)Math.floor(Math.random()*(loc-0+1)+0);
			}
			
			//generate a string to store the locations of each generated Agent
			if(i<AgentCount-1) {
				AgentString=AgentString+""+agentX+","+agentY+",";
			}
			else {
				AgentString=AgentString+""+agentX+","+agentY+";";
			}
			
			
			//Assign Agents to generated locations
			gridBool[agentX][agentY] = true;
			grid[agentX][agentY] = "Agent";
		}
		
	}

	public static String solve(String grid, String strategy, boolean visualize) {
		plan = "";
		noOfExpandedNodes = 0;
		deaths = 0;
		killss = 0;
		RepeatedNodes.removeAll(RepeatedNodes); 
		
		ArrayList<String> Operators = new ArrayList<String>();
		Operators.add("up");
		Operators.add("down");
		Operators.add("left");
		Operators.add("right");
		Operators.add("carry");
		Operators.add("drop");
		Operators.add("takePill");
		Operators.add("kill");
		Operators.add("fly");
		
		Node sol = GeneralSearch(grid, strategy, Operators);
		if(sol == null) {
			return "No Solution";
		}
		//String finalsol = sol.getNeoState();
		return plan + ";" + deaths + ";" + killss + ";" + noOfExpandedNodes;
//		if(strategy == "BF") {
//			
//		}
		
	}
	
	public static Node InitialState(String grid) {
		
		//traverse the grid to generate an initial state
		String[] parts = grid.split(";");
		
		String MxN = parts[0];
		
		String Neoloc = parts[2];
		
		String Agents = parts[4];
		//System.out.println(Agents.length());
		//String HostagesCarried= parts[5];
		String Hostages = parts[7];
		String[] HostageParts = Hostages.split(",");
		List<String> Hostages1 = new ArrayList<String>(Arrays.asList(HostageParts));
		
		int noOfHostages = Hostages1.size()/3;
//		for(int i = 0; i <= Hostages1.size()-3; i = i +3) {
//			noOfHostages++;
//		}
		
		//System.out.println("Hostages  "+Hostages);
		
		String Pads = parts[6];
		
		String Pills = parts[5];
		//Pairs in one elemenmt in Pads?
		
		String Telephone=parts[3];
		String[] TParts = Telephone.split(",");
		TelephoneX=Integer.parseInt(TParts[0]);
		TelephoneY=Integer.parseInt(TParts[1]);
		
		MaxhostageCarry=Integer.parseInt(parts[1]);
		String H2A = "-1,-1";
		if(Agents.length() == 0) {
			Agents = "-1,-1";
		}
		if(Hostages.length() == 0) {
			Hostages = "-1,-1,-10000000";
		}
		if(Pads.length() == 0) {
			Pads = "-1,-1";
		}
		if(Pills.length() == 0) {
			Pills = "-1,-1";
		}
		//System.out.println("HC"+HostagesCarried.length());
		String State = MxN + ";" + Neoloc + "," + "0" + ";" + Agents + ";" + Hostages + ";" + "0" + ";" + H2A + ";" + Pads + ";" + Pills + ";" + "0" + ";" + "0" + ";"+ "-1,-1,-10000000" + ";" + noOfHostages + ";" + "0" + ";";  
		
		//use the generated initial state above to generate the root node and return it
		Node Root = new Node(State);
		//GeneralSearch(Grid,Root);
		return Root;
	}

	public static Node GeneralSearch(String grid, String strategy, ArrayList<String> Operators) {
		
		//Queue<Node> SearchQueue = new LinkedList<Node>();
		LinkedList<Node> SearchQueue = new LinkedList<Node>();
		Node Root = InitialState(grid);
		SearchQueue.add(Root);
		RepeatedNodes.add(Root.NeoState);
		//String rh = RemoveHostages(Root.NeoState);
		//RepeatedNodes.add(rh);
		
		ArrayList<String> copyop = new ArrayList<String>(Operators);
		
		
		if(strategy.equals("ID")) {
			int depth = 0;
			SearchQueue.remove();
			for(int j = 0; j <= depth; j++) {
				SearchQueue.add(Root);
				while(!SearchQueue.isEmpty()) {
					noOfExpandedNodes ++;
					Node currentNode = SearchQueue.removeFirst();
//					Queue<Node> temp = new LinkedList<Node>();
//					temp.add(currentNode);
					//check if goal
					if(goaltest(currentNode)) {
						return currentNode;
					}
					
					Operators = new ArrayList<String>(copyop); 
					ArrayList<String> cOperators = illegalOps(currentNode, Operators);
					boolean DepthisGreater = false;
					for(int i = 0; i < cOperators.size(); i++) {
						String newstate = Move(cOperators.get(i),currentNode); //new state after applying each operator
						
						if(!(newstate.equals(" "))) {
							String RemoveHostagesState = RemoveDamage(newstate);
							
							if(!RepeatedNodes.contains(RemoveHostagesState)) {
								Node newNode = new Node(newstate,0,currentNode.depth,currentNode,0, cOperators.get(i));	//Creating new node
								RepeatedNodes.add(RemoveHostagesState);
								//Enqueue at the beginning of the search queue
								
								SearchQueue.addFirst(newNode);
								if(newNode.depth > depth) {
									DepthisGreater = true;
									break;
								}
							}
						}
					
					}
					if(DepthisGreater) {
						j = 0;
						RepeatedNodes.removeAll(RepeatedNodes);
						while(!SearchQueue.isEmpty()) {
							SearchQueue.remove();
						}
						
						break;
					}
				}
				depth++;
			}
		}
		
		else if(strategy.equals("UC")) {
			
			while(!SearchQueue.isEmpty()) {
				noOfExpandedNodes ++;
				Node currentNode = SearchQueue.removeFirst();
				//check if goal
				if(goaltest(currentNode)) {
					return currentNode;
				}
				
				Operators = new ArrayList<String>(copyop); 
				ArrayList<String> cOperators = illegalOps(currentNode, Operators);
				
				for(int i = 0; i < cOperators.size(); i++) {
					String newstate = Move(cOperators.get(i),currentNode); //new state after applying each operator
					
					if(!(newstate.equals(" "))) {
						String RemoveHostagesState = RemoveDamage(newstate);
						
						if(!RepeatedNodes.contains(RemoveHostagesState)) {
							
							Node newNode = new Node(newstate,Cost(currentNode),currentNode.depth,currentNode,0, cOperators.get(i));	//Creating new node
							RepeatedNodes.add(RemoveHostagesState);

							//Queue<Node> temp = new LinkedList<Node>();
							//temp.add(newNode);
							
							//Take this new node and compare with all existing searchqueue elements
							//newNode
							//Searchqueue.get(i)
							sortSearchQueue(SearchQueue,newNode);
						}
					}
				
				}
				//System.out.println(SearchQueue);
			}
		
			
			
		}
		else if(strategy.equals("GR1")) {
			while(!SearchQueue.isEmpty()) {
				noOfExpandedNodes ++;
				Node currentNode = SearchQueue.removeFirst();
				//check if goal
				if(goaltest(currentNode)) {
					return currentNode;
				}
				
				Operators = new ArrayList<String>(copyop); 
				ArrayList<String> cOperators = illegalOps(currentNode, Operators);
				
				for(int i = 0; i < cOperators.size(); i++) {
					String newstate = Move(cOperators.get(i),currentNode); //new state after applying each operator
					
					if(!(newstate.equals(" "))) {
						String RemoveHostagesState = RemoveDamage(newstate);
						
						if(!RepeatedNodes.contains(RemoveHostagesState)) {
							
							//Node nnode = new Node(newstate);
							Node newNode = new Node(newstate,heuristic1(currentNode),currentNode.depth,currentNode,0, cOperators.get(i));	//Creating new node
							RepeatedNodes.add(RemoveHostagesState);

							//Queue<Node> temp = new LinkedList<Node>();
							//temp.add(newNode);
							
							//Take this new node and compare with all existing searchqueue elements
							//newNode
							//Searchqueue.get(i)
							sortSearchQueue(SearchQueue,newNode);
						}
					}
				
				}
				//System.out.println(SearchQueue);
			}
		}
		else if(strategy.equals("GR2")) {
			while(!SearchQueue.isEmpty()) {
				System.out.println("NEW");
				noOfExpandedNodes ++;
				Node currentNode = SearchQueue.removeFirst();
				//check if goal
				if(goaltest(currentNode)) {
					return currentNode;
				}
				
				Operators = new ArrayList<String>(copyop); 
				ArrayList<String> cOperators = illegalOps(currentNode, Operators);
				
				for(int i = 0; i < cOperators.size(); i++) {
					String newstate = Move(cOperators.get(i),currentNode); //new state after applying each operator
					
					if(!(newstate.equals(" "))) {
						String RemoveHostagesState = RemoveDamage(newstate);
						
						if(!RepeatedNodes.contains(RemoveHostagesState)) {
							
							Node newNode = new Node(newstate,heuristic2(currentNode),currentNode.depth,currentNode,0, cOperators.get(i));	//Creating new node
							RepeatedNodes.add(RemoveHostagesState);

							//Queue<Node> temp = new LinkedList<Node>();
							//temp.add(newNode);
							
							//Take this new node and compare with all existing searchqueue elements
							//newNode
							//Searchqueue.get(i)
							sortSearchQueue(SearchQueue,newNode);
						}
					}
				
				}
				//System.out.println(SearchQueue);
			}
		}
		else if(strategy.equals("AS1")) {
			while(!SearchQueue.isEmpty()) {
				System.out.println("NEW");
				noOfExpandedNodes ++;
				Node currentNode = SearchQueue.removeFirst();
				//check if goal
				if(goaltest(currentNode)) {
					return currentNode;
				}
				
				Operators = new ArrayList<String>(copyop); 
				ArrayList<String> cOperators = illegalOps(currentNode, Operators);
				
				for(int i = 0; i < cOperators.size(); i++) {
					String newstate = Move(cOperators.get(i),currentNode); //new state after applying each operator
					
					if(!(newstate.equals(" "))) {
						String RemoveHostagesState = RemoveDamage(newstate);
						
						if(!RepeatedNodes.contains(RemoveHostagesState)) {
							
							Node newNode = new Node(newstate,heuristic1(currentNode)+Cost(currentNode),currentNode.depth,currentNode,0, cOperators.get(i));	//Creating new node
							RepeatedNodes.add(RemoveHostagesState);

							//Queue<Node> temp = new LinkedList<Node>();
							//temp.add(newNode);
							
							//Take this new node and compare with all existing searchqueue elements
							//newNode
							//Searchqueue.get(i)
							sortSearchQueue(SearchQueue,newNode);
						}
					}
				
				}
				//System.out.println(SearchQueue);
			}
		}
		else if(strategy.equals("AS2")) {
			while(!SearchQueue.isEmpty()) {
				System.out.println("NEW");
				noOfExpandedNodes ++;
				Node currentNode = SearchQueue.removeFirst();
				//check if goal
				if(goaltest(currentNode)) {
					return currentNode;
				}
				
				Operators = new ArrayList<String>(copyop); 
				ArrayList<String> cOperators = illegalOps(currentNode, Operators);
				
				for(int i = 0; i < cOperators.size(); i++) {
					String newstate = Move(cOperators.get(i),currentNode); //new state after applying each operator
					
					if(!(newstate.equals(" "))) {
						String RemoveHostagesState = RemoveDamage(newstate);
						
						if(!RepeatedNodes.contains(RemoveHostagesState)) {
							
							Node newNode = new Node(newstate,heuristic2(currentNode)+Cost(currentNode),currentNode.depth,currentNode,0, cOperators.get(i));	//Creating new node
							RepeatedNodes.add(RemoveHostagesState);

							//Queue<Node> temp = new LinkedList<Node>();
							//temp.add(newNode);
							
							//Take this new node and compare with all existing searchqueue elements
							//newNode
							//Searchqueue.get(i)
							sortSearchQueue(SearchQueue,newNode);
						}
					}
				
				}
				//System.out.println(SearchQueue);
			}
		}
		
		else {
			
			while(!SearchQueue.isEmpty()) {
				noOfExpandedNodes ++;
				Node currentNode = SearchQueue.removeFirst();
				//check if goal
				if(goaltest(currentNode)) {
					return currentNode;
				}
				
				Operators = new ArrayList<String>(copyop); 
				ArrayList<String> cOperators = illegalOps(currentNode, Operators);
				
				for(int i = 0; i < cOperators.size(); i++) {
					String newstate = Move(cOperators.get(i),currentNode); //new state after applying each operator
					
					if(!(newstate.equals(" "))) {
						String RemoveHostagesState = RemoveDamage(newstate);
				
						if(!RepeatedNodes.contains(RemoveHostagesState)) {
							Node newNode = new Node(newstate,0,currentNode.depth,currentNode,0, cOperators.get(i));	//Creating new node
							RepeatedNodes.add(RemoveHostagesState);
							//SearchQueue = BreadthFirst(SearchQueue,newNode);
							//System.out.println(newstate);
							
							checkstrategy(strategy,SearchQueue,newNode);
						}
					}
				
				}
			}
		}
		return null;
	}
	
	public static void checkstrategy(String strategy, LinkedList<Node> SearchQueue, Node node) {
		if(strategy.equals("BF")) {
			BreadthFirst(SearchQueue, node);
		}
		else if(strategy.equals("DF")) {
			DepthFirst(SearchQueue, node);
		}
//		else if(strategy.equals("ID")) {
//			
//		}
	}
	
	public static void BreadthFirst(LinkedList<Node> SearchQueue, Node node) {
		//Enqueue at the end of the search queue
		SearchQueue.addLast(node);
		//return SearchQueue;
	}
	
	public static void DepthFirst(LinkedList<Node> SearchQueue, Node node) {
		//Enqueue at the beginning of the search queue
		SearchQueue.addFirst(node);
	}
	
	public static void sortSearchQueue(LinkedList<Node> SearchQueue, Node newNode) {
		
		
		for(int j=0; j<SearchQueue.size(); j++) {
			
			if (newNode.pathCost < SearchQueue.get(j).pathCost) {
				Node temp = SearchQueue.get(j);
				SearchQueue.add(j, newNode);
				newNode = temp;
				//System.out.println("NEWxxx: " + SearchQueue.get(j));
			}
			
		}
		SearchQueue.addLast(newNode);
		//return SearchQueue;
		
	}
	
	public static int Cost(Node node) {
		String state = node.getNeoState();
		String[] parts = state.split(";");
		int akills = Integer.parseInt(parts[8]);
		int death = Integer.parseInt(parts[9]);
		
		int pathCost = (death * 40) + (akills * 50); 
		return pathCost;
		
		//BEFORE EXPANSION
		//Expand root
		//Path cost = death count * small value + kill count * big value
		//Priority queue / linked list and sort
		//Everytime you add in queue you will sort
	}
	
	public static ArrayList<String> illegalOps(Node currentNode, ArrayList<String> Operators) {
		
		//Get Neo state
		//Eliminate illegal moves from operators based on this current Neo state
		String currentState = currentNode.getNeoState();
	
		
		//Traverse our current state to get some information;
		String[] parts = currentState.split(";");
		
		// Get Grid Size
		String MxN = parts[0];
		String[] gridsizeParts = MxN.split(",");
		int gridsize = Integer.parseInt(gridsizeParts[0]);
		
		//Get Neo's current location and damage
		String Neo = parts[1];
		String[] NeoParts = Neo.split(",");
		
		

		int Neoxx = Integer.parseInt(NeoParts[0]);
		int Neoyy = Integer.parseInt(NeoParts[1]);
		int NeoDamage = Integer.parseInt(NeoParts[2]);
		
		//Get number of hostages Neo is carrying
		int HostagesCarried = Integer.parseInt(parts [4]);
		
		//Get the hostages that turned to agents
		String Hostages2Agents = parts[5];
		String[] Hostage2AgentsParts = Hostages2Agents.split(",");
		List<String> Hostages2Agents1 = new ArrayList<String>(Arrays.asList(Hostage2AgentsParts));
		
		//NEO DROP RESTRICTIONS
		//NEO NOT AT TELEPHONE BOOTH
		if(Neoxx != TelephoneX || Neoyy != TelephoneY) {
			Operators.remove("drop");
		}
		//NEO AT TELEPHONEBOOTH BUT NOT CARRYING Hostages
		if((Neoxx == TelephoneX && Neoyy == TelephoneY) && (HostagesCarried == 0)) { //condition of Neo not carrying anything 
			Operators.remove("drop");
		}		
		
		//Get Agent Location to compare it to Neo's Location
		//Create boolean agentNeighborNeo to keep track of Neo's location relative to an agent
		boolean agentNeighborNeo = false;
		String Agents = parts[2];
		String[] AgentParts = Agents.split(",");
		for(int i=0; i<=AgentParts.length-2; i = i+2) {
	
			int Agentxx = Integer.parseInt(AgentParts[i]);
			int Agentyy = Integer.parseInt(AgentParts[i+1]);
			
			
			
			//if Neo is above or below an agent set boolean to true
			if((Neoxx == (Agentxx-1) || Neoxx == (Agentxx+1)) && (Neoyy == Agentyy)) {
				agentNeighborNeo = true;
			}
			//if Neo is next to an agent set boolean to true
			if((Neoyy == (Agentyy-1) || Neoyy == (Agentyy+1)) && (Neoxx == Agentxx)) {
				agentNeighborNeo = true;
			}
			//Neo cannot be with an agent in the same cell (remove RIGHT/LEFT/UP/DOWN operators)
			if((Neoyy == (Agentyy-1)) && Neoxx == Agentxx ) {
				Operators.remove("right");
			}
			else if((Neoyy == (Agentyy+1)) && Neoxx == Agentxx) {
				Operators.remove("left");
			}
			if((Neoxx == (Agentxx+1)) && Neoyy == Agentyy) {
				Operators.remove("up");
			}
			else if((Neoxx == (Agentxx-1)) && Neoyy == Agentyy) {
				Operators.remove("down");
			}
			}
		
		
		//Get locations of hostages that turned to agents to compare it to Neo's Location
		if(!Hostages2Agents.isEmpty()) {
			for(int i=0; i<=Hostages2Agents1.size()-2; i = i+2) {
				int Agentxx = Integer.parseInt(Hostages2Agents1.get(i));
				int Agentyy = Integer.parseInt(Hostages2Agents1.get(i+1));
				
				//Neo cannot Kill a hostage that turned to an agent if he is not in a neighboring cell
				//set the boolean to true if they are not in neighboring cells
				if((Neoxx == (Agentxx-1) || Neoxx == (Agentxx+1)) && (Neoyy == Agentyy)) {
					agentNeighborNeo = true;
				}
				
				//Neo cannot be with a hostage that turned to an agent in the same cell (remove RIGHT/LEFT/UP/DOWN operators)
				if((Neoyy == (Agentyy-1) || Neoyy == (Agentyy+1)) && (Neoxx == Agentxx)) {
					agentNeighborNeo = true;
				}
				
				//Cant be with an agent in the same cell
				if((Neoyy == (Agentyy-1)) && Neoxx == Agentxx ) {
					Operators.remove("right");
				}
				else if((Neoyy == (Agentyy+1)) && Neoxx == Agentxx) {
					Operators.remove("left");
				}
				if((Neoxx == (Agentxx+1)) && Neoyy == Agentyy) {
					Operators.remove("up");
				}
				else if((Neoxx == (Agentxx-1)) && Neoyy == Agentyy) {
					Operators.remove("down");
				}
			}
		}
		//Neo cannot Kill an agent if he is not in a neighboring cell (remove KILL operator)
		if(!agentNeighborNeo) {
			Operators.remove("kill");
		}
		
		//Get Pill location to compare it to Neo's
		//Create boolean pillAtNeo to keep track of Neo's location relative to pills
		String Pills = parts[7];
		boolean pillAtNeo = false;
		String[] PillParts = Pills.split(",");
		for(int i=0; i<=PillParts.length -2; i = i+2) {
			int Pillxx = Integer.parseInt(PillParts[i]);
			int Pillyy = Integer.parseInt(PillParts[i+1]);
			if(Neoxx == Pillxx && Neoyy == Pillyy) {
				pillAtNeo = true;
			}
		}
		//Neo cannot take pill if they are not in the same cell (remove TAKEPILL operator)
		if(!pillAtNeo || NeoDamage == 0) {
			Operators.remove("takePill");
		}
		
		//Get Pads location to compare it to Neo's
		//Create boolean padAtNeo to keep track of Neo's location relative to pads
		String Pads = parts[6];
		boolean padAtNeo = false;
		String[] PadParts = Pads.split(",");
		for(int i=0; i<=PadParts.length/2 - 2; i = i+2) {
			int Padxx = Integer.parseInt(PadParts[i]);
			int Padyy = Integer.parseInt(PadParts[i+1]);
			
			//Check if the locations of Neo and a pad are the same
			if(Neoxx == Padxx && Neoyy == Padyy) {
				padAtNeo = true;
			}
		}
		if(!padAtNeo) {
			Operators.remove("fly");
		}

		
		//Get Hostage location to compare it to Neo's
		//Create boolean hostageAtNeo to keep track of Neo's location relative to Hostages
		//Set to true if both Neo and a hostage are in the same cell
		String Hostages = parts [3];
		boolean h = false;
		String[] HostageParts = Hostages.split(",");
		for(int i=0; i <= HostageParts.length-3; i = i+3) {
			int Hostagexx = Integer.parseInt(HostageParts[i]);
			int Hostageyy = Integer.parseInt(HostageParts[i+1]);
			int HostageDam = Integer.parseInt(HostageParts[i+2]);
			
			if(Neoxx == Hostagexx && Neoyy == Hostageyy) {
				//System.out.print(Neoxx + " " + Neoyy + " " + Hostagexx + " " + Hostageyy);
				h = true;
			}
			
			//Cannot kill an agent if neo is in the same cell with a Hosatge and Hostage Damage is greater than 98
			if(Neoxx == Hostagexx && Neoyy == Hostageyy && HostageDam >= 98) {
				Operators.remove("kill"); 
			}
			//Cannot go to a cell with a living hostage with damage of 98 or 99
			//add neox = hostage 
			if((Neoyy == (Hostageyy-1) && Neoxx == Hostagexx) && (HostageDam >= 98)) {
				Operators.remove("right");
			}
			else if((Neoyy == (Hostageyy+1)&& Neoxx == Hostagexx) && (HostageDam >= 98)) {
				Operators.remove("left");
			}
			if((Neoxx == (Hostagexx+1) && Neoyy == Hostageyy) && (HostageDam >= 98)) {
				Operators.remove("up");
			}
			else if((Neoxx == (Hostagexx-1) && Neoyy == Hostageyy) && (HostageDam >= 98)) {
				Operators.remove("down");
			}
			
		}
		//Cannot carry a hostage if neo is not in the same cell
		if(h==false || MaxhostageCarry == HostagesCarried) {
			Operators.remove("carry");
		}
		
		//Cannot move outside of grid boundaries
		if(Neoxx == 0) {
			Operators.remove("up");
		}
		else if(Neoxx == gridsize - 1) {
			Operators.remove("down");
		}
		
		if(Neoyy == 0) {
			Operators.remove("left");
		}else if(Neoyy == gridsize - 1) {
			Operators.remove("right");
		}
		
		//Return the list of ONLY valid operators
		System.out.println(" ");
		System.out.println("Current Node:	" + currentNode.getNeoState());
		System.out.println("Current Operators:	" + Operators.toString());
		System.out.println(" ");
		System.out.println("Hostages Carried: " + HostagesCarried);
		System.out.println(" ");
		
		return Operators;
		
		
	}

	public static String Move(String Op, Node currentNode) {
		//Get current state and traverse it
		String currentState = currentNode.getNeoState();
		String newstate = "";
		
		String[] parts = currentState.split(";");
		
		String MxN = parts[0];
		
		String Neo = parts[1];
		String[] NeoParts = Neo.split(",");
		int Neoxx = Integer.parseInt(NeoParts[0]);
		int Neoyy = Integer.parseInt(NeoParts[1]);
		int NeoDamage = Integer.parseInt(NeoParts[2]);
		
		String Agents = parts [2];
		String[] AgentParts = Agents.split(",");
		List<String> Agents1 = new ArrayList<String>(Arrays.asList(AgentParts));
		
		String Hostages = parts [3];
		String[] HostageParts = Hostages.split(",");
		List<String> Hostages1 = new ArrayList<String>(Arrays.asList(HostageParts));
		//System.out.println("HOST INIT: " + Hostages1);
		
		int hostagescarried = Integer.parseInt(parts[4]);
		
		String HostagesC = parts [10];
		String[] HostageCarriedParts = HostagesC.split(",");
		List<String> HostagesCarried1 = new ArrayList<String>(Arrays.asList(HostageCarriedParts));
		
		
		String Hostages2Agents = parts[5];
		String[] Hostage2AgentsParts = Hostages2Agents.split(",");
		List<String> Hostages2Agents1 = new ArrayList<String>(Arrays.asList(Hostage2AgentsParts));
		
		String Pads = parts[6];
		
		String Pills = parts [7];
		String[] PillParts = Pills.split(",");
		List<String> Pills1 = new ArrayList<String>(Arrays.asList(PillParts));
		
		int akills = Integer.parseInt(parts[8]);
		
		int death = Integer.parseInt(parts[9]);
		
		int noOfHostagesrem = Integer.parseInt(parts[11]);
		
		int noOfHostages2Agents = Integer.parseInt(parts[12]);
		
		boolean Pilltaken = false;
		
		if(Op == "up") {
			Neoxx = Neoxx - 1;
		}
		else if(Op == "down") {
			Neoxx = Neoxx + 1;
		}
		else if(Op == "left") {
			Neoyy = Neoyy - 1;
		}
		else if(Op == "right") {
			Neoyy = Neoyy + 1;
		}
		else if(Op == "carry") {
			hostagescarried += 1;
			for(int i=0; i<=Hostages1.size()-3; i = i+3) {
				int Hostagexx = Integer.parseInt(Hostages1.get(i));
				int Hostageyy = Integer.parseInt(Hostages1.get(i+1));
				int HostageDam = Integer.parseInt(Hostages1.get(i+2));
				if(Neoxx == Hostagexx && Neoyy == Hostageyy) {
					HostageDam = HostageDam - 2;
					Hostages1.set(i+2, Integer.toString(HostageDam));
					HostagesCarried1.add(Hostages1.remove(i));
					HostagesCarried1.add(Hostages1.remove(i));
					HostagesCarried1.add(Hostages1.remove(i));
					noOfHostagesrem -= 1;
					break;
					
					
				}
			}
		}
		else if(Op == "drop") {
			hostagescarried = 0;
//			for(int i = 0; i < HostagesCarried1.size(); i++) {
//					HostagesCarried1.remove(i);
//					i--;
//				}
			for(int i = 0; i <= HostagesCarried1.size() - 3; i=i+3) {
//				String Hostagexx = HostagesCarried1.get(i);
//				String Hostageyy = HostagesCarried1.get(i+1);
				int HostageDam = Integer.parseInt(HostagesCarried1.get(i+2));
//				HostageDam = HostageDam + 2;
//				HostagesCarried1.set(i+2, Integer.toString(HostageDam));
				//if hostage carried damage is equal 100 increment death count
				if(HostageDam >= 100) {
					death++;
				}
				HostagesCarried1.remove(i+2);
				HostagesCarried1.remove(i+1);
				HostagesCarried1.remove(i);
				i=i-3;
			}
				
			
		}
		else if(Op == "takePill") {
			for(int i=0; i<=Pills1.size()-2; i = i+2) {
				int pillxx = Integer.parseInt(Pills1.get(i));
				int pillyy = Integer.parseInt(Pills1.get(i+1));
				if((Neoxx == pillxx) && (Neoyy == pillyy)) {
					NeoDamage = NeoDamage - 20;
					Pills1.remove(i+1);
					Pills1.remove(i);
				}
			}
			for(int i = 0; i <= Hostages1.size()-3; i=i+3) {
				int HostageDam = Integer.parseInt(Hostages1.get(i+2));
				HostageDam = HostageDam - 20;
				Hostages1.set(i+2, Integer.toString(HostageDam));
			}
			for(int i = 0; i <= HostagesCarried1.size()-3; i=i+3) {
				int HostageDam = Integer.parseInt(HostagesCarried1.get(i+2));
				HostageDam = HostageDam - 20;
				HostagesCarried1.set(i+2, Integer.toString(HostageDam));
			}
			Pilltaken = true;
		}
		
		else if(Op == "kill") {
			//boolean isKill = false;
			for(int i=0; i<=Hostages2Agents1.size()-2; i = i+2) {
				int agentxx = Integer.parseInt(Hostages2Agents1.get(i));
				int agentyy = Integer.parseInt(Hostages2Agents1.get(i+1));
				if((Neoxx == agentxx-1 && Neoyy==agentyy) || (Neoxx == agentxx+1 && Neoyy==agentyy) || (Neoyy == agentyy-1 && Neoxx==agentxx) || (Neoyy == agentyy+1 && Neoxx==agentxx)) {
					akills++;
					Hostages2Agents1.remove(i+1);
					Hostages2Agents1.remove(i);
					i=i-2;
					noOfHostages2Agents--;
					//isKill = true;
					//break;
				}
			}
			
			for(int i=0; i<=Agents1.size()-2; i = i+2) {
				int agentxx = Integer.parseInt(Agents1.get(i));
				int agentyy = Integer.parseInt(Agents1.get(i+1));
				if((Neoxx == agentxx-1 && Neoyy==agentyy) || (Neoxx == agentxx+1 && Neoyy==agentyy) || (Neoyy == agentyy-1 && Neoxx==agentxx) || (Neoyy == agentyy+1 && Neoxx==agentxx)) {
					akills++;
					Agents1.remove(i+1);
					Agents1.remove(i);
					i=i-2;
					//break;
						
				}
			}
			
			
			NeoDamage = NeoDamage + 20;
			
		}
		else if(Op == "fly") {
			String[] PadParts = Pads.split(",");
			//Get Location of other Pad
			for(int j = 0; j <= PadParts.length-4; j = j + 4) {
				int Padxx = Integer.parseInt(PadParts[j]);
				int Padyy = Integer.parseInt(PadParts[j+1]);
				if(Neoxx == Padxx && Neoyy == Padyy) {
					Neoxx = Integer.parseInt(PadParts[j+2]);
					Neoyy = Integer.parseInt(PadParts[j+3]);
				}
			}
		}
		
		
		if(!Pilltaken) {
			for(int i = 0; i <= Hostages1.size() - 3; i=i+3) {
				String Hostagexx = Hostages1.get(i);
				String Hostageyy = Hostages1.get(i+1);
				int HostageDam = Integer.parseInt(Hostages1.get(i+2));
				HostageDam = HostageDam + 2;
				Hostages1.set(i+2, Integer.toString(HostageDam));
				//if hostage damage is equal 100 remove from hostages and add the to hostages turned to agents
				if(HostageDam >= 100) {
					Hostages1.remove(i+2);
					Hostages1.remove(i+1);
					Hostages1.remove(i);
					death++;
					i=i-3;
					noOfHostagesrem--;
//					if(Hostages2Agents1.size() != 0) {
//						if(Hostages2Agents1.get(0).equals("") ) {
//							Hostages2Agents1.remove(0);
//						}
//						if(Hostages2Agents1.get(0).equals(" ") ) {
//							Hostages2Agents1.remove(0);
//						}
//					}
					Hostages2Agents1.add(Hostagexx);
					Hostages2Agents1.add(Hostageyy);
					noOfHostages2Agents++;
					//comma added here
				}
			}
			
			for(int i = 0; i <= HostagesCarried1.size() - 3; i=i+3) {
//				String Hostagexx = HostagesCarried1.get(i);
//				String Hostageyy = HostagesCarried1.get(i+1);
				int HostageDam = Integer.parseInt(HostagesCarried1.get(i+2));
				HostageDam = HostageDam + 2;
				HostagesCarried1.set(i+2, Integer.toString(HostageDam));
				//if hostage carried damage is equal 100 increment death count
//				if(HostageDam >= 100) {
////					HostagesCarried1.remove(i+2);
////					HostagesCarried1.remove(i+1);
////					HostagesCarried1.remove(i);
//					death++;
////					i=i-3;
//				}
			}
		}
	
		if(NeoDamage >= 100) {
			//System.out.println("Neo is Dead");
			return " ";
		}
//		if(hostagescarried == 0) {
//			newstate = MxN + ";" + Neoxx + "," + Neoyy + "," + NeoDamage + ";" + String.join(",", Agents1) + ";" + String.join(",", Hostages1) + ";" + hostagescarried + ";" + String.join(",", Hostages2Agents1) + ";" + Pads + ";" + String.join(",", Pills1) + ";" + akills + ";" + death + ";" + " " + ";" + noOfHostagesrem + ";" +  noOfHostages2Agents + ";";
//			System.out.println(newstate);
//			return newstate;
//		}
		if(noOfHostagesrem == 0) {
			Hostages1.removeAll(Hostages1);
			Hostages1.add("-1");
			Hostages1.add("-1");
			Hostages1.add("-100000000");
		}
		if(noOfHostages2Agents == 0) {
			Hostages2Agents1.removeAll(Hostages2Agents1);
			Hostages2Agents1.add("-1");
			Hostages2Agents1.add("-1");
		}
		if(hostagescarried == 0) {
			HostagesCarried1.removeAll(HostagesCarried1);
			HostagesCarried1.add("-1");
			HostagesCarried1.add("-1");
			HostagesCarried1.add("-100000000");
		}
		if(Agents1.size() == 0) {
			Agents1.add("-1");
			Agents1.add("-1");
		}
		if(Pills1.size() == 0) {
			Pills1.add("-1");
			Pills1.add("-1");
		}
		newstate = MxN + ";" + Neoxx + "," + Neoyy + "," + NeoDamage + ";" + String.join(",", Agents1) + ";" + String.join(",", Hostages1) + ";"
		+ hostagescarried + ";" + String.join(",", Hostages2Agents1) + ";" + Pads + ";" + String.join(",", Pills1) + ";" + akills + ";" 
				+ death + ";" + String.join(",", HostagesCarried1) + ";" + noOfHostagesrem + ";" +  noOfHostages2Agents + ";";
//		System.out.println(HostagesCarried1.size());
//		System.out.println(Hostages1.size());
		//System.out.println(newstate);
		return newstate;
		
	}

	public static boolean goaltest(Node node) {
		//Get current state of neo (from current node)
		String state = node.getNeoState();
		
		//Traverse the state to get info to check for goal
		String[] parts = state.split(";");
		
		//Get Neo location & damage
		String Neo = parts[1];
		String[] NeoParts = Neo.split(",");
		int Neoxx = Integer.parseInt(NeoParts[0]);
		int Neoyy = Integer.parseInt(NeoParts[1]);
		int NeoDamage = Integer.parseInt(NeoParts[2]);
		
		//Get hostages
		String Hostages = parts [3];
		String[] HostageParts = Hostages.split(",");
		List<String> Hostages1 = Arrays.asList(HostageParts);
		
		int hostagescarried = Integer.parseInt(parts[4]);
		
		//Get hostages that turned into agents
		String Hostages2Agents = parts[5];
		String[] Hostage2AgentsParts = Hostages2Agents.split(",");
		List<String> Hostages2Agents1 = Arrays.asList(Hostage2AgentsParts);
		
		int akills = Integer.parseInt(parts[8]);
		
		int death = Integer.parseInt(parts[9]);
		
		int noOfHostagesrem = Integer.parseInt(parts[11]);
		//System.out.println(noOfHostagesrem);
		
		int noOfHostages2Agents = Integer.parseInt(parts[12]);
		
		
		//Check if Neo is at telephone booth and there are neither hostages nor hostages that turned into agents
		//then GOAL
		if(Neoxx == TelephoneX && Neoyy == TelephoneY) {
			//System.out.println("Hostages2Agents1: " + Hostages2Agents1);
			//System.out.println("HOSTAGES1 Size:" + Hostages1.size());
			//System.out.println("HOSTAGES2Agents Size:" + Hostages2Agents1.size());
			//if(Hostages1.size() < 4) { //HARDCODE
			if(noOfHostagesrem == 0) {
//			if(Hostages1.equals("")) { 
				//if(Hostages2Agents1.size() < 4) { //HARDCODE
				if(noOfHostages2Agents == 0) {
//				if(Hostages2Agents1.equals(" ") || Hostages2Agents1.equals("")) { 
					if(hostagescarried == 0) {
						while(node.prevOp != null) {
							//System.out.println(TelephoneX + "," + TelephoneY);
							plan = node.getPrevOp() + "," + plan;
							node = node.getParentNode();
							System.out.println(node.NeoState);
						}
						plan = plan.substring(0, plan.length()-1);
						System.out.println("This is goaltest");
						System.out.println(state);
						killss = akills;
						deaths = death;
						//System.out.println(RepeatedNodes.size());
						return true;
					}
					
				}
			}
		}
		
		return false;
	}

	public static int heuristic1(Node node) {
		String state = node.getNeoState();
		String[] parts = state.split(";");
		//Get Neo's current location and damage
		String Neo = parts[1];
		String[] NeoParts = Neo.split(",");

		int Neoxx = Integer.parseInt(NeoParts[0]);
		int Neoyy = Integer.parseInt(NeoParts[1]);
		
		int akills = Integer.parseInt(parts[8]);
		int death = Integer.parseInt(parts[9]);
		
		int noOfHostages2Agents = Integer.parseInt(parts[12]);
		
		int pathCost = (death * 4) + (akills * 100); 
		
		int heuristic = pathCost/3;
		int distance = Math.abs(Neoxx-TelephoneX) +  Math.abs(Neoyy-TelephoneY);
		if(distance == 0 && goaltest(node)) {
			return 0;
		}
		
		return heuristic;
	}
	
	public static int heuristic2(Node node) {
		String state = node.getNeoState();
		String[] parts = state.split(";");
		//Get Neo's current location and damage
		String Neo = parts[1];
		String[] NeoParts = Neo.split(",");

		int Neoxx = Integer.parseInt(NeoParts[0]);
		int Neoyy = Integer.parseInt(NeoParts[1]);
		
		int akills = Integer.parseInt(parts[8]);
		int death = Integer.parseInt(parts[9]);
		
		int pathCost = (death * 20) + (akills * 100); 
		
		int heuristic = pathCost/9;
		int distance = Math.abs(Neoxx-TelephoneX) +  Math.abs(Neoyy-TelephoneY);
		if(distance == 0 && goaltest(node)) {
			return 0;
		}
		return heuristic;
	}
	
	public static String RemoveDamage(String state) {
		
		String[] parts = state.split(";");
		
		String Neo = parts[1];
		
		String Agents = parts [2];
		
		String Hostages = parts [3];
		String[] HostageParts = Hostages.split(",");
		List<String> Hostages1 = new ArrayList<String>(Arrays.asList(HostageParts));
		
		int hostagescarried = Integer.parseInt(parts[4]);
		
		String Hostages2Agents = parts[5];
		
		String Pads = parts[6];
		
		String Pills = parts [7];
		
		int akills = Integer.parseInt(parts[8]);
		
		int death = Integer.parseInt(parts[9]);
		
		String HostagesC = parts [10];
		String[] HostageCarriedParts = HostagesC.split(",");
		List<String> HostagesCarried1 = new ArrayList<String>(Arrays.asList(HostageCarriedParts));
		
		int noOfHostagesrem = Integer.parseInt(parts[11]);
		
		int noOfHostages2Agents = Integer.parseInt(parts[12]);
		
		for(int i = 0; i <= Hostages1.size() - 3; i=i+3) {
			Hostages1.remove(i+2);
			i--;
		}
		
		for(int i = 0; i <= HostagesCarried1.size() - 3; i=i+3) {
			HostagesCarried1.remove(i+2);
			i--;
		}
		
		String removed = Neo + ";" + Agents + ";" + String.join(",", Hostages1) + ";" + hostagescarried + ";" + Hostages2Agents + ";" + Pads + ";" + Pills + ";" 
		+ akills + ";" + death + ";" + String.join(",", HostagesCarried1) + ";" + noOfHostagesrem + ";" + noOfHostages2Agents;
		
		return removed;
	}
}