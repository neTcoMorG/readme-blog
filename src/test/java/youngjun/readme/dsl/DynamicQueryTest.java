package youngjun.readme.dsl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import youngjun.readme.domain.entity.user.QUser;
import youngjun.readme.domain.entity.user.User;
import youngjun.readme.domain.repository.UserRepository;
import youngjun.readme.domain.service.user.UserService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static youngjun.readme.domain.entity.user.QUser.*;

@SpringBootTest
@Transactional
public class DynamicQueryTest {

    @PersistenceContext
    EntityManager em;

    JPAQueryFactory query;

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void init () {
        query = new JPAQueryFactory(em);

        User test1 = new User("test1@naver.com", "@test1", "test", "profile");
        User test2 = new User("test2@naver.com", "@test2", "test", "profile");
        User test3 = new User("test3@naver.com", "@test3", "test", "profile");
        User test4 = new User("test4@naver.com", "@test4", "test", "profile");
        User test5 = new User("test5@naver.com", "@test5", "test", "profile");

        em.persist(test1);
        em.persist(test2);
        em.persist(test3);
        em.persist(test4);
        em.persist(test5);
    }

    @Test
    void dynamicQueryBooleanBuilder () {
        String userTag = "@test1";
        String userEmail = "test2@naver.com";

        List<User> result = searchUserV1(userTag, userEmail);
        assertEquals(result.size(), 2);
    }

    private List<User> searchUserV1(String userTagCond, String userEmailCond) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (userTagCond != null) {
            booleanBuilder.and(user.tag.eq(userTagCond));
        }
        if (userEmailCond != null) {
            booleanBuilder.or(user.email.eq(userEmailCond));
        }

        List<User> fetch = query.selectFrom(user)
                .where(booleanBuilder)
                .fetch();

        return fetch;
    }

    @Test
    void dynamicQueryWhere () {
        String userTag = "@test1";
        String userEmail = null;

        List<User> result = searchUserV2(userTag, userEmail);
        assertEquals(result.size(), 1);
    }

    private List<User> searchUserV2(String userTag, String userEmail) {
        return query.selectFrom(user)
                .where(userTagEq(userTag), userEmailEq(userEmail))
                .fetch();
    }

    private Predicate userEmailEq(String userEmail) {
        return userEmail != null ? user.email.eq(userEmail) : null;
    }

    private Predicate userTagEq(String userTag) {
        return userTag != null ? user.tag.eq(userTag) : null;
    }
}
