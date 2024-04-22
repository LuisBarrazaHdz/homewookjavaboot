package expeditors.backend.adoption;

public class FilterDTO {
    private TypeFilter columnName;

    private Object columnValue;

    public FilterDTO(TypeFilter columnName, Object columnValue) {
        this.columnName = columnName;
        this.columnValue = columnValue;
    }

    public TypeFilter getColumnName() {
        return columnName;
    }

    public void setColumnName(TypeFilter columnName) {
        this.columnName = columnName;
    }

    public Object getColumnValue() {
        return columnValue;
    }

    public void setColumnValue(Object columnValue) {
        this.columnValue = columnValue;
    }
}
