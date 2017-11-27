package library.dao.repositories.impl;

import library.domain.IHaveId;
import library.dao.mappers.IMapper;
import library.dao.repositories.IRepository;
import library.dao.uow.Entity;
import library.dao.uow.IUnitOfWork;
import library.dao.uow.IUnitOfWorkRepository;


public abstract class RepositoryBase <TEntity extends IHaveId>
    implements IRepository<TEntity>, IUnitOfWorkRepository {
}
