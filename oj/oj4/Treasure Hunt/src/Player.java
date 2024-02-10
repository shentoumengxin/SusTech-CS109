public class Player {    //处理一下step的更新，注意如果step5，而2步越界，则step+=2，还要记录一个总的step方便后续判断。
    private static int number=1;
    private final int id=number;
    private int score=0;
    private int steps=0;
    private Position position;
    private Map map;
    private  int maxStepAllowed;

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public int getSteps() {
        return steps;
    }

    public Position getPosition() {
        return position;
    }

    public Player(Map map, Position initialPosition){
        this.map=map;
        this.position=initialPosition;
        maxStepAllowed=99999999;
        number++;
    }
    public Player(Map map, Position initialPosition, int maxStepAllowed){
        this.map=map;
        this.position=initialPosition;
        this.maxStepAllowed=maxStepAllowed;
        number++;
    }
    public boolean judge(Position position,int step_c,int step_r){
        if(position.getCol()+step_c>map.getColumns()||position.getCol()+step_c<0||position.getRow()+step_r> map.getRows()||position.getRow()+step_r<0){
            return false;
        }else{
            return true;
        }
    }
    public boolean move(Direction direction,int steps){
        map.checkActive();
        if(!map.isActive())return false;
        if(maxStepAllowed==0)return false;
        switch (direction){
            case UP ->{
                if(judge(position,0,-steps)&&maxStepAllowed-steps>=0){
                    Position find=new Position(position.getRow(), position.getCol());
                    position.setRow(position.getRow()-steps);
                    for(; find.getRow()>=position.getRow();find.setRow(find.getRow()-1)){
                        int point=map.hasTreasure(find);
                        if(point!=0){
                            score+=point;
                            map.update(find);
                        }
                        if(!map.isActive()){
                            position.setRow(find.getRow());
                            return false;
                        }
                        maxStepAllowed--;
                        this.steps++;
                    }
                    //if(maxStepAllowed==0)return false;
                    maxStepAllowed++;
                    this.steps--;
                    return true;
                }else{
                    Position find=new Position(position.getRow(), position.getCol());
                    if(position.getRow()-maxStepAllowed>0){
                        position.setRow(position.getRow()-maxStepAllowed);
                    }else {
                        position.setRow(0);
                    }

                    for(; find.getRow()>=position.getRow()&&maxStepAllowed>=0;find.setRow(find.getRow()-1)){
                        int point=map.hasTreasure(find);
                        if(point!=0){
                            score+=point;
                            map.update(find);
                        }
                        if(!map.isActive()){
                            position.setRow(find.getRow());
                            return false;
                        }
                        maxStepAllowed--;
                        this.steps++;
                    }
                    //if(maxStepAllowed==0)return false;
                    maxStepAllowed++;
                    this.steps--;

                    return false;
                }
            }
            case DOWN ->{
                if(judge(position,0,steps)&&maxStepAllowed-steps>=0){
                    Position find=new Position(position.getRow(), position.getCol());
                    position.setRow(position.getRow()+steps);
                    for(; find.getRow()<=position.getRow();find.setRow(find.getRow()+1)){

                        int point=map.hasTreasure(find);
                        if(point!=0){
                            score+=point;
                            map.update(find);
                        }
                        if(!map.isActive()){
                            position.setRow(find.getRow());
                            return false;
                        }
                        maxStepAllowed--;
                        this.steps++;
                    }
                    maxStepAllowed++;
                    this.steps--;
                    return true;
                }else{
                    Position find=new Position(position.getRow(), position.getCol());
                    if(position.getRow()+maxStepAllowed<map.getRows()){
                        position.setRow(position.getRow()+maxStepAllowed);
                    }else {
                        position.setRow(map.getRows());
                    }
                    for(; find.getRow()<=position.getRow()&&maxStepAllowed>=0;find.setRow(find.getRow()+1)){
                        int point=map.hasTreasure(find);
                        if(point!=0){
                            score+=point;
                            map.update(find);
                        }
                        if(!map.isActive()){
                            position.setRow(find.getRow());
                            return false;
                        }
                        maxStepAllowed--;
                        this.steps++;

                    }
                    //if(maxStepAllowed==0)return false;
                    maxStepAllowed++;
                    this.steps--;
                    return false;
                }
            }
            case LEFT ->{
                if(judge(position,-steps,0)&&maxStepAllowed-steps>=0){
                    Position find=new Position(position.getRow(), position.getCol());
                    position.setCol(position.getCol()-steps);
                    for(; find.getCol()>=position.getCol();find.setCol(find.getCol()-1)){
                        int point=map.hasTreasure(find);
                        if(point!=0){
                            score+=point;
                            map.update(find);
                        }
                        if(!map.isActive()){
                            position.setCol(find.getCol());
                            return false;
                        }
                        maxStepAllowed--;
                        this.steps++;
                    }
                    maxStepAllowed++;
                    this.steps--;
                    return true;
                }else{
                    Position find=new Position(position.getRow(), position.getCol());
                    if(position.getCol()-maxStepAllowed>0){
                        position.setCol(position.getCol()-maxStepAllowed);
                    }else {
                        position.setCol(0);
                    }
                    for(; find.getCol()>=position.getCol()&&maxStepAllowed>=0;find.setCol(find.getCol()-1)){
                        int point=map.hasTreasure(find);
                        if(point!=0){
                            score+=point;
                            map.update(find);
                        }
                        if(!map.isActive()){
                            position.setCol(find.getCol());
                            return false;
                        }
                        maxStepAllowed--;
                        this.steps++;
                    }
                    if(maxStepAllowed==0)return false;
                    maxStepAllowed++;
                    this.steps--;
                    return false;
                }
            }
            case RIGHT ->{
                if(judge(position,steps,0)&&maxStepAllowed-steps>=0){
                    Position find=new Position(position.getRow(), position.getCol());
                    position.setCol(position.getCol()+steps);
                    for(; find.getCol()<=position.getCol();find.setCol(find.getCol()+1)){
                        int point=map.hasTreasure(find);
                        if(point!=0){
                            score+=point;
                            map.update(find);
                        }
                        if(!map.isActive()){
                            position.setCol(find.getCol());
                        }
                        maxStepAllowed--;
                        this.steps++;

                    }
                    maxStepAllowed++;
                    this.steps--;
                    return true;
                }else{
                    Position find=new Position(position.getRow(), position.getCol());
                    if(position.getCol()+maxStepAllowed<map.getColumns()){
                        position.setCol(position.getCol()+maxStepAllowed);
                    }else {
                        position.setCol(map.getColumns());
                    }
                    for(; find.getCol()<=position.getCol()&&maxStepAllowed>=0;find.setCol(find.getCol()+1)){
                        int point=map.hasTreasure(find);
                        if(point!=0){
                            score+=point;
                            map.update(find);
                        }
                        if(!map.isActive()){
                            position.setCol(find.getCol());
                            return false;
                        }
                        maxStepAllowed--;
                        this.steps++;

                    }
                    //if(maxStepAllowed==0)return false;
                    maxStepAllowed++;
                    this.steps--;
                    return false;
                }
            }
        }
        return false;
    }
    public boolean equals(Object player){
        Player p=(Player)player;
        return this.getId()==p.getId();
    }

}
