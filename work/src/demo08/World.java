package demo08;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;

public class World {

    private ArrayList<Entity> team;
    private String name;

    public World(String name) {
        this.name = name;
        team = new ArrayList<>();

    }

    public ArrayList<Entity> getTeam() {
        return team;
    }

    public void setTeam(ArrayList<Entity> team) {
        this.team = team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Entity getEntity(int pos) {
        return team.get(pos);
    }

    public void addEntity(Entity entity) {
        team.add(entity);
    }

    public Entity deletEntity(int pos) {
        return team.remove(pos);
    }
}
