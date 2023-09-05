package youngjun.readme.domain.repository.querydsl;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import youngjun.readme.domain.dto.response.QResponseUser;
import youngjun.readme.domain.dto.response.QResponseUserPost;
import youngjun.readme.domain.dto.response.ResponseUserPost;

import static youngjun.readme.domain.entity.post.QPost.post;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryDsl {

    private final JPAQueryFactory query;

    @Override
    public Page<ResponseUserPost> getPostsRecent (Pageable pageable) {
        QueryResults<ResponseUserPost> queryResults =
                query.select(new QResponseUserPost(
                        new QResponseUser(post.writer.email, post.writer.tag, post.writer.profile_url),
                            post.title, post.content, post.modified))
                        .from(post)
                        .orderBy(post.created.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetchResults();

        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    @Override
    public Page<ResponseUserPost> getPostsVote (Pageable pageable) {
        QueryResults<ResponseUserPost> queryResults =
                query.select(new QResponseUserPost(
                                new QResponseUser(post.writer.email, post.writer.tag, post.writer.profile_url),
                                post.title, post.content, post.modified))
                        .from(post)
                        .orderBy(post.voteCount.desc())
                        .offset(pageable.getOffset())
                        .limit(pageable.getPageSize())
                        .fetchResults();

        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    @Override
    public Page<ResponseUserPost> getPostsViewer (Pageable pageable) {
        return null;
    }
}
