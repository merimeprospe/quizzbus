package quizzbus.commun;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import quizzbus.data.Compte;


@Mapper
public interface IMapper {
	
	Compte update( @MappingTarget Compte target, Compte source  );
	
}
