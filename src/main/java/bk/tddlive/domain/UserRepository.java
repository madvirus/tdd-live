package bk.tddlive.domain;

public interface UserRepository {
    User findById(String id);
}
