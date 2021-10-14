package com.motafelipe.api.backoffice.services;

import com.motafelipe.api.backoffice.dto.response.RoomResponseDto;
import com.motafelipe.api.backoffice.exception.NotFoundException;
import com.motafelipe.api.backoffice.models.RoomModel;
import com.motafelipe.api.backoffice.dto.response.PageResponseDto;
import com.motafelipe.api.backoffice.dto.request.PageRequestDto;
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

    /**
     * createNewRoom
     * @param RoomResponseDto - Room dto object.
     * @return - RoomResponseDto
     */
    public RoomResponseDto createNewRoom (RoomResponseDto RoomResponseDto){

        var data = Optional.of(roomRepository.save(RoomResponseDto.toEntity()));

        data.orElseThrow(
            () -> new HttpServerErrorException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    " We cannot to save room number : " + RoomResponseDto.getNumber() +
                            " in our database, please try it again in a few moment."
            )
        );

        return RoomResponseDto.toDto(data.get());
    }

    /**
     * updateRoom
     * @param RoomResponseDto - Room dto object.
     * @return RoomResponseDto
     */
    public RoomResponseDto updateRoom(RoomResponseDto RoomResponseDto){

        var result =
            this.roomRepository
                .getRoomByIdRoom(RoomResponseDto.getIdRoom())
                .map(
                    resource -> {
                        resource.setNumber(RoomResponseDto.getNumber() == null ? resource.getNumber() : RoomResponseDto.getNumber());
                        resource.setActivated(RoomResponseDto.isActivated());
                        return this.roomRepository.save(resource);
                    }
                )
                .orElseThrow(
                    () ->
                    new HttpServerErrorException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        " We cannot to save room number : " + RoomResponseDto.getNumber() +
                        " in our database, please try it again in a few moment."
                    )
                );

        return RoomResponseDto.toDto(result);
    }

    /**
     * deleteRoomById
     * @param idUser - idUser
     */
    public void deleteRoomById(Long idUser){
        this.roomRepository.deleteById(idUser);
    }

    /**
     *
     * @param idRoom - idRoom
     * @return - RoomResponseDto
     */
    public RoomResponseDto getById(Long idRoom){

        var data =  this.roomRepository.getRoomByIdRoom(idRoom);

        data.orElseThrow(
            () -> new NotFoundException("There are not room with id = " + idRoom)
        );

        return new RoomResponseDto().toDto(data.get());
    }

    /**
     * getAllRooms
     * @param pr - pr
     * @return - PageResponseDto<RoomResponseDto>
     */
    public PageResponseDto<RoomResponseDto> getAllRooms (PageRequestDto pr) {
        return RoomModel.entityPageToPageModel(this.roomRepository.findAll(PageRequest.of(pr.getPage(), pr.getSize())));
    }
}
