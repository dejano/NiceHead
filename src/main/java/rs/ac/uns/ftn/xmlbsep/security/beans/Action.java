package rs.ac.uns.ftn.xmlbsep.security.beans;

import org.mongodb.morphia.annotations.Reference;

public class Action {

    @Reference
    private Operation operation;
    @Reference
    private RbacObject object;

    public Action() {

    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }

    public RbacObject getObject() {
        return object;
    }

    public void setObject(RbacObject object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "Action{" +
                "operation=" + operation +
                ", object=" + object +
                '}';
    }
}


