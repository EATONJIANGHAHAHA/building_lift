package model;

import java.awt.event.ActionEvent;
import java.util.*;
import javafx.application.*;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

/**
 * A Lift carries people to their destinations.
 */
public class Lift {
    private IntegerProperty number = new SimpleIntegerProperty();
    private IntegerProperty bottom = new SimpleIntegerProperty();
    private IntegerProperty top = new SimpleIntegerProperty();
    private IntegerProperty level = new SimpleIntegerProperty();
    private IntegerProperty direction = new SimpleIntegerProperty();
    private ObservableList<Person> passengers =FXCollections.observableArrayList();
    private ObservableList<Person> queue = FXCollections.observableArrayList();
    private StringProperty liftStatus = new SimpleStringProperty();
    private StringProperty levelVisual = new SimpleStringProperty();
    private IntegerProperty passengersSize = new SimpleIntegerProperty();
    private IntegerProperty queueSize = new SimpleIntegerProperty();

    public Lift(int number, int bottom, int top, int level) {
        this.number.set(number);
        this.bottom.set(bottom);
        this.top.set(top);
        this.level.set(level);
        this.direction.set(Direction.STATIONARY);
        this.liftStatus.set("--");
        liftStatus.set("");
        levelVisual.set("");
        passengersSize.set(passengers.size());
        queueSize.set(queue.size());
    }

    // PROPERTIES
    
    public int getPassengersSize(){
        return passengersSize.get();
    }public ReadOnlyIntegerProperty PassengersSizeProperty(){
        return passengersSize;
    }
    
    public int getQueueSize(){
        return queueSize.get();
    }public ReadOnlyIntegerProperty QueueSizeProperty(){
        return queueSize;
    }
    
    
    public String getLiftStatus(){
        return liftStatus.get();
    }public StringProperty liftStatusProperty(){
        return liftStatus;
    }

    public int getNumber() {
        return number.get();
    }public IntegerProperty NumberProperty(){
        return number;
    }
    

    public int getBottom() {
        return bottom.get();
    }public IntegerProperty bottomProperty(){
        return bottom;
    }

    public int getTop() {
        return top.get();
    }public IntegerProperty topProperty(){
        return top;
    }

    public int getLevel() {
        return level.get();
    }public IntegerProperty levelProperty(){
        return level;
    }

    public int getDirection() {
        return direction.get();
    }public IntegerProperty directionProperty(){
        return level;
    }

    public final ObservableList<Person> getPassengers() {
        return passengers;
    }

    public final ObservableList<Person> getQueue() {
        return queue;
    }

    // FUNCTIONS and PROCEDURES

    public void call(Person person) {
        queue.add(person);
        synchSize();
    }

    private void board(Person person) {
        queue.remove(person);
        passengers.add(person);
        person.board();
        synchSize();
    }

    private void alight(Person person) {
        passengers.remove(person);
        person.alight();
        synchSize();
    }
    
    private void synchSize(){
        passengersSize.set(passengers.size());
        queueSize.set(queue.size());
    }

    /**
     * This procedure carries out the operation of a lift for one step of time.
     * It is intended to be called repeatedly.
     */
    public void operate() {
        // This is slightly different from Assignment 1
        levelVisual.set("");
        Person nextPerson = nextPerson();
        if (direction.get() == Direction.STATIONARY) {
            if (nextPerson != null)
                direction.set(nextPerson.liftDirection(getLevel()));
        }
        if (alight() || board()) {
            if (passengers.isEmpty())
                direction.set(Direction.STATIONARY);
        }
        else {
            move();
            if (isAtBoundary() || passengers.isEmpty() && anyoneWaitingHere())
                direction.set(Direction.STATIONARY);
        }
        if(direction.get()==1)
            liftStatus.set("UP");
        else if(direction.get()==-1)
            liftStatus.set("DOWN");
        else
            liftStatus.set("--");
	for (int i = 0; i < bottom.get(); i++)
            levelVisual.set(levelVisual.get()+" ");
            levelVisual.set(levelVisual.get()+"|");
            for (int i = bottom.get(); i <= top.get(); i++)
               	if (i == level.get())
                    levelVisual.set(levelVisual.get()+level.get());
		else
                    levelVisual.set(levelVisual.get()+" ");
		levelVisual.set(levelVisual.get()+"|");
    }

    private boolean anyoneWaitingHere() {
        if (queue.isEmpty())
            return false;
        Person person = queue.get(0);
        return person.isAt(level.get());
    }

    private void move() {
        level.set(level.get() + direction.get());
        for (Person person : passengers)
            person.move(direction.get());
    }

    /**
     * Determine the next person to service.
     */
    private Person nextPerson() {
        // Take the next passenger if there is one
        if (passengers.size() > 0)
            return passengers.get(0);
        // Otherwise go to pick up the next waiting if there is one
        else if (queue.size() > 0)
            return queue.get(0);
        // Otherwise there is no next person
        else
            return null;
    }

    private boolean board() {
        int count = 0;
        for (Person person : new LinkedList<Person>(queue))
            if (person.canBoard(getLevel(), direction.get())) {
                board(person);
                count++;
            }
        return count > 0;
    }

    private boolean alight() {
        int count = 0;
        for (Person person : new ArrayList<Person>(passengers))
            if (person.hasArrived()) {
                alight(person);
                count++;
            }
        return count > 0;
    }

    private boolean isAtBoundary() {
        return getLevel() == bottom.get() || getLevel() == top.get();
    }

    private int distanceTo(int target) {
        return Math.abs(target - getLevel());
    }

    public int suitability(int distance, int start, int destination) {
        if (!canTake(start, destination))
            return 0;
        else if (direction.get() * Direction.fromTo(getLevel(), start) < 0)
            return 1;
        else if (direction.get() == -Direction.fromTo(start, destination))
            return distance + 1 - distanceTo(start);
        else
            return distance + 2 - distanceTo(start);
    }

    private boolean canTake(int start, int destination) {
        return canReach(start) && canReach(destination);
    }

    private boolean canReach(int level) {
        return level >= bottom.get() && level <= top.get();
    }
    
    public String getLevelVisual(){
        return levelVisual.get();
    }public ReadOnlyStringProperty levelVisualProperty(){
        return levelVisual;
    }
    

    @Override
    public String toString() {
        return "Lift " + number.get();
    }
}
