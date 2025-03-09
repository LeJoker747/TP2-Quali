package iut;

class Player {
    private int health;
    private Faction faction; 

    Player(){
        this.health = 100;
        this.faction = null;
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
            opponent.health -= 10;
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
        if (opponent.getFaction() == (this.getFaction())){
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
}

