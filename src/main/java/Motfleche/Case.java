package Motfleche;

public class Case {
    private CaseType type;
    private Definition definition;

    public Case(CaseType type) {
        this.type = type;
    }

    public CaseType getType() {
        return type;
    }

    public void setType(CaseType type) {
        this.type = type;
    }

    public Definition getDefinition() {
        return definition;
    }

    public void setDefinition(Definition definition) {
        this.definition = definition;
    }
}

enum CaseType {
    Lettre,
    Definition,
    Vide
}
