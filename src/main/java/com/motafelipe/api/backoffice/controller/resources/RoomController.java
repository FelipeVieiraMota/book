package com.motafelipe.api.backoffice.controller.resources;

import com.motafelipe.api.backoffice.controller.resources.interfaces.BasicInterface;
import com.motafelipe.api.backoffice.dto.EnvelopedData;
import com.motafelipe.api.backoffice.dto.request.CustomerRequestDto;
import com.motafelipe.api.backoffice.dto.request.PageRequestDto;
import com.motafelipe.api.backoffice.dto.request.RoomRequestDto;
import com.motafelipe.api.backoffice.dto.response.CustomerResponseDto;
import com.motafelipe.api.backoffice.dto.response.PageResponseDto;
import com.motafelipe.api.backoffice.dto.response.RoomResponseDto;
import com.motafelipe.api.backoffice.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value="/aws/backoffice/v1/rooms")
public class RoomController implements BasicInterface<RoomResponseDto, RoomRequestDto> {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Get all resources by lazy loading.
     * @param page - page
     * @param size - size of page.
     * @return ResponseEntity<PageModel<RoomResponseDto>> HTTP STATUS 200 OK
     */
    @GetMapping
    public ResponseEntity<PageResponseDto<RoomResponseDto>> getPaginated(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        PageRequestDto pr = new PageRequestDto(page, size);
        PageResponseDto<RoomResponseDto> pm = this.roomService.getPaginated(pr);
        return ResponseEntity.ok(pm);
    }

    /**
     * save
     * @param roomRequestDto - roomRequestDto
     * @return - ResponseEntity<EnvelopedData<RoomResponseDto>>
     */
    @PostMapping()
    public ResponseEntity<EnvelopedData<RoomResponseDto>> save (@RequestBody @Valid RoomRequestDto roomRequestDto){
        var result = this.roomService.save(roomRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EnvelopedData<>(result));
    }

    /**
     * deleteById
     * @param id - id
     * @return void
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable(name="id") Long id) {
        this.roomService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * update
     * @param id - id
     * @param roomRequestDto - roomRequestDto
     * @return - ResponseEntity<EnvelopedData<RoomResponseDto>>
     */
    @PutMapping("/{id}")
    public ResponseEntity<EnvelopedData<RoomResponseDto>> update(@PathVariable(name="id") Long id, @RequestBody @Valid RoomRequestDto roomRequestDto){
        var result = roomService.update(id, roomRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new EnvelopedData<>(result));
    }
}
