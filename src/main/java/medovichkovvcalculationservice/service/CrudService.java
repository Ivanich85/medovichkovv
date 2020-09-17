package medovichkovvcalculationservice.service;

public interface CrudService<T, ID> {
    T save(T object);

    default T getById(ID id) {
        return null;
    };

    default boolean delete(ID id) {
        return false;
    };
}
