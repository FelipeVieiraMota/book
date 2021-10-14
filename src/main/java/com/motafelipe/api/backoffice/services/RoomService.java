package com.motafelipe.api.backoffice.services;

import com.motafelipe.api.backoffice.dto.RoomDto;
import com.motafelipe.api.backoffice.exception.NotFoundException;
import com.motafelipe.api.backoffice.models.RoomModel;
import com.motafelipe.api.backoffice.models.pagination.PageModel;
import com.motafelipe.api.backoffice.models.pagination.PageRequestModel;
import com.motafelipe.api.backoffice.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;

@Service
public class RoomService {

    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }


    public RoomDto save (RoomDto RoomDto){

        var data = Optional.of(roomRepository.save(RoomDto.toEntity()));

        data.orElseThrow(
            () -> new HttpServerErrorException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    " We cannot to save room number : " + RoomDto.getNumber() +
                            " in our database, please try it again in a few moment."
            )
        );

        return RoomDto.toDto(data.get());
    }

    /**
     * @param RoomDto - Room dto object.
     * @return RoomDto
     */
    public RoomDto update(RoomDto RoomDto){

        var result =
                this.roomRepository
                        .getRoomByIdRoom(RoomDto.getIdRoom())
                        .map(
                            resource -> {
                                resource.setNumber(RoomDto.getNumber() == null ? resource.getNumber() : RoomDto.getNumber());
                                resource.setActivated(RoomDto.isActivated());
                                return this.roomRepository.save(resource);
                            }
                        )
                        .orElseThrow(
                            () ->
                            new HttpServerErrorException(
                                HttpStatus.INTERNAL_SERVER_ERROR,
                                " We cannot to save room number : " + RoomDto.getNumber() +
                                " in our database, please try it again in a few moment."
                            )
                        );

        return RoomDto.toDto(result);
    }


    public void deleteById(Long idUser){
        this.roomRepository.deleteById(idUser);
    }

    public RoomDto getById(Long idUser){

        var data =  this.roomRepository.getRoomByIdRoom(idUser);

        data.orElseThrow(
            () -> new NotFoundException("There are not room with id = " + idUser)
        );

        return new RoomDto().toDto(data.get());
    }

    public PageModel<RoomDto> getPagination (PageRequestModel pr) {
        return RoomModel.entityPageToPageModel(this.roomRepository.findAll(PageRequest.of(pr.getPage(), pr.getSize())));
    }
}
