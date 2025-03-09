package iut;



class Player {
    private int health;
    private Faction faction; 
    private FighterType fighterType;
    private int place;

    Player(){
        this.health = 100;
        this.faction = null;
        this.fighterType = FighterType.MELEE;
        this.place = (int)(Math.random()*11);
    }

    public int health(){return this.health;}

    public boolean isAlive(){
        if (this.health <= 0){
            return false;
        }
        return true;   
    }

    public void hit(Player opponent){
        if (opponent.isAlive() && !this.isAlly(opponent)){
            if (this.getFighterType() == FighterType.MELEE && this.getDistance(opponent) <= 2){
                opponent.health -= 10;
            } else if  (this.getFighterType() == FighterType.RANGED && this.getDistance(opponent) <= 20) {
                opponent.health -= 10;
            }
        }
    }

    public void heal(){
        if (this.isAlive()){
            this.health += 10;
        }
    }

    public void heal(Player target){
        if (this.isAlly(target)){
            target.heal();
        }
    }

    public boolean isAlly(Player opponent){
        if (this.getFaction() != null && opponent.getFaction() == (this.getFaction())){
            return true;
        }
        return false;
    }

    public Faction getFaction(){return this.faction;}

    public void joinFaction(Faction faction){
        if (this.faction == null) {
            this.faction = faction;
        }
    }

    public void leaveFaction(){
        this.faction = null;
    }

    public void setFighterType (FighterType fighterType){
        this.fighterType = fighterType;
    }

    public FighterType getFighterType(){return this.fighterType;}

    public int getPlace(){return this.place;}

    public int getDistance(Player opponent){return Math.abs(this.getPlace()-opponent.getPlace());}

    public void move(int movement){this.place += movement;}
}

