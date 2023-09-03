[readme-blog SQL]

CREATE TABLE USER (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	email VARCHAR(255) NOT NULL,
	tag VARCHAR(255) NOT NULL,
	about VARCHAR(255) NOT NULL,
	profile_url VARCHAR(255) NOT NULL,

	UNIQUE INDEX idx_tag_id (tag, id)
);

CREATE TABLE INFORMATION (
	user_id 	BIGINT NOT NULL,
	linkedin VARCHAR(255),
	github 	VARCHAR(255),
	site		VARCHAR(255),

	FOREIGN KEY (user_id) REFERENCES user(id)
		ON DELETE CASCADE
);

CREATE TABLE follow (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
	follower_id BIGINT NOT NULL,
	following_id BIGINT NOT NULL,
	created DATETIME NOT NULL,

	FOREIGN KEY (follower_id) REFERENCES user(id)
		ON DELETE CASCADE,

	FOREIGN KEY (following_id) REFERENCES user(id)
		ON DELETE CASCADE,

	INDEX follower_id_idx(follower_id),
	INDEX following_idx(following_id)
);

CREATE TABLE post (
	id 			BIGINT PRIMARY KEY AUTO_INCREMENT,
	writer_id 	BIGINT NOT NULL,
	series_id 	BIGINT,
	title 		VARCHAR(255) NOT NULL,
	content 		LONGTEXT NOT NULL,
	created 		DATETIME NOT NULL,
	modified 	DATETIME NOT NULL,
	vote_count 	BIGINT NOT NULL,

	INDEX idx_writer_id(writer_id),
	INDEX idx_series_id(series_id)
);

CREATE TABLE POST_VOTE (
	post_id BIGINT NOT NULL,
	voter_id BIGINT NOT NULL,
	vote_date DATETIME NOT NULL,

	FOREIGN KEY (post_id) REFERENCES post(id)
		ON DELETE CASCADE
);

CREATE TABLE series (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	owner_id BIGINT NOT NULL,
	series_name VARCHAR(255) NOT NULL,
	created DATETIME NOT NULL,

	FOREIGN KEY (owner_id) REFERENCES user(id)
		ON DELETE CASCADE,

	INDEX idx_owner_id(owner_id)
);

CREATE TABLE post_comment (
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	commenter_id BIGINT NOT NULL,
	post_id BIGINT NOT NULL,
	txt VARCHAR(255) NOT NULL,
	created DATETIME,
	modified DATETIME,

	FOREIGN KEY (post_id) REFERENCES post(id)
		ON DELETE CASCADE,

	FOREIGN KEY (commenter_id) REFERENCES user(id)
		ON DELETE CASCADE,

	INDEX idx_post_id(post_id)
);

CREATE TABLE user_view_history (
	user_id BIGINT NOT NULL,
	post_id BIGINT NOT NULL,
	read_date DATETIME NOT NULL,

	FOREIGN KEY (user_id) REFERENCES user(id)
		ON DELETE CASCADE,

	FOREIGN KEY (post_id) REFERENCES post(id)
		ON DELETE CASCADE,

	UNIQUE INDEX idx_user_id_post_id(user_id, post_id)
);

CREATE TABLE tag(
	id BIGINT PRIMARY KEY AUTO_INCREMENT,
	post_id BIGINT NOT NULL,
	tag VARCHAR(50) NOT NULL,

	FOREIGN KEY (post_id) REFERENCES post(id)
		ON DELETE CASCADE
);