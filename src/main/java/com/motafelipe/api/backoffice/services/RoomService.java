package com.motafelipe.api.backoffice.services;

import com.motafelipe.api.backoffice.dto.request.RoomRequestDto;
import com.motafelipe.api.backoffice.dto.response.RoomResponseDto;
import com.motafelipe.api.backoffice.exception.NotFoundException;
import com.motafelipe.api.backoffice.models.RoomModel;
import com.motafelipe.api.backoffice.dto.response.PageResponseDto;
import com.motafelipe.api.backoffice.dto.request.PageRequestDto;
import com.motafelipe.api.backoffice.repositories.RoomRepository;
import com.motafelipe.api.backoffice.services.interfaces.BasicInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;

@Service
public class RoomService implements BasicInterface<RoomResponseDto, RoomRequestDto> {

    private RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public RoomResponseDto save(RoomRequestDto roomRequestDto) {
        var data =
                Optional.of(roomRepository.save(roomRequestDto.toEntity()));

        data.orElseThrow(
                () -> new HttpServerErrorException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        " We cannot to save room number : " + roomRequestDto.getNumber() +
                                " in our database, please try it again in a few moment."
                )
        );
        return new RoomResponseDto().toDto(data.get());
    }

    @Override
    public RoomResponseDto update(Long id, RoomRequestDto roomRequestDto) {

        var request = new RoomResponseDto(id, roomRequestDto);

        var entity =
        this.roomRepository
            .getRoomByIdRoom(request.getIdRoom())
            .map(
                resource -> {
                    resource.setNumber(request.getNumber() == null ? resource.getNumber() : request.getNumber());
                    resource.setActivated(request.isActivated());
                    return this.roomRepository.save(resource);
                }
            )
            .orElseThrow(
                () ->
                new HttpServerErrorException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    " We cannot to save room number : " + roomRequestDto.getNumber() +
                            " in our database, please try it again in a few moment."
                )
            );

        return request.toDto(entity);
    }

    @Override
    public void delete(Long id) {
        this.roomRepository.deleteById(id);
    }

    @Override
    public PageResponseDto<RoomResponseDto> getPaginated(PageRequestDto pr) {
        return new RoomModel().entityPageToPageModel(this.roomRepository.findAll(PageRequest.of(pr.getPage(), pr.getSize())));
    }

    @Override
    public RoomResponseDto getById(Long id) {

        var data =  this.roomRepository.getRoomByIdRoom(id);

        data.orElseThrow(
                () -> new NotFoundException("There are not room with id = " + id)
        );

        return new RoomResponseDto().toDto(data.get());
    }
}
