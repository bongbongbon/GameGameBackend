package com.project.resumeservice.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QResume is a Querydsl query type for Resume
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QResume extends EntityPathBase<Resume> {

    private static final long serialVersionUID = -1105992492L;

    public static final QResume resume = new QResume("resume");

    public final QBaseEntity _super = new QBaseEntity(this);

    public final ListPath<Career, QCareer> careers = this.<Career, QCareer>createList("careers", Career.class, QCareer.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final ListPath<String, StringPath> selectedJobs = this.<String, StringPath>createList("selectedJobs", String.class, StringPath.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QResume(String variable) {
        super(Resume.class, forVariable(variable));
    }

    public QResume(Path<? extends Resume> path) {
        super(path.getType(), path.getMetadata());
    }

    public QResume(PathMetadata metadata) {
        super(Resume.class, metadata);
    }

}

