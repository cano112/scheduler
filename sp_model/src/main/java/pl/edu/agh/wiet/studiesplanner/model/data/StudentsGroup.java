package pl.edu.agh.wiet.studiesplanner.model.data;

public final class StudentsGroup {
    private final int id;

    public StudentsGroup(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StudentsGroup that = (StudentsGroup) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
