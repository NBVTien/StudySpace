package com.example.studyspace.domainv2.content.aggregates;

import com.example.studyspace.domainv2.content.valueobjects.ContentId;
import com.example.studyspace.domainv2.shared.models.Entity;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Content extends Entity<ContentId> {
    protected String title;
    protected int version;

    public Content(ContentId id, LocalDateTime createdAt, LocalDateTime updatedAt, String title, int version) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.version = version;
    }

    public static Content create(ContentId id, String title) {
        return new Content(
            id,
            LocalDateTime.now(),
            LocalDateTime.now(),
            title,
            1
        );
    }

    public void updateTitle(String newTitle) {
        this.title = newTitle;
        this.version++;
        this.update();
    }

    public void update() {
        this.version++;
        super.update();
    }
}