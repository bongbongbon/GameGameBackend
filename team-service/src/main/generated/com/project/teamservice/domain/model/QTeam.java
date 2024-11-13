package com.project.teamservice.domain.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTeam is a Querydsl query type for Team
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTeam extends EntityPathBase<Team> {

    private static final long serialVersionUID = 953421844L;

    public static final QTeam team = new QTeam("team");

    public final QBaseEntity _super = new QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final StringPath createdBy = _super.createdBy;

    public final StringPath description = createString("description");

    public final StringPath domain = createString("domain");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final BooleanPath isDeleted = _super.isDeleted;

    public final NumberPath<Integer> memberNumber = createNumber("memberNumber", Integer.class);

    public final DatePath<java.time.LocalDate> projectEndDate = createDate("projectEndDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> projectStartDate = createDate("projectStartDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> recruitmentEndDate = createDate("recruitmentEndDate", java.time.LocalDate.class);

    public final DatePath<java.time.LocalDate> recruitmentStartDate = createDate("recruitmentStartDate", java.time.LocalDate.class);

    public final EnumPath<TeamCategory> teamCategory = createEnum("teamCategory", TeamCategory.class);

    public final StringPath title = createString("title");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    //inherited
    public final StringPath updatedBy = _super.updatedBy;

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QTeam(String variable) {
        super(Team.class, forVariable(variable));
    }

    public QTeam(Path<? extends Team> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTeam(PathMetadata metadata) {
        super(Team.class, metadata);
    }

}

