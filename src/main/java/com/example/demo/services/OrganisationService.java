package com.example.demo.services;

import com.example.demo.entity.organisation.Organisation;

import java.util.List;

public interface OrganisationService extends ItemService<Organisation> {
    int getCountByParentId(long parentId);
    List<Organisation> findAllByParentId(long parentId, int offset, int limit);
}
