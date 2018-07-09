package com.firebase.message.controller;

import com.firebase.message.InstanceRepository;
import com.firebase.message.repository.InstanceDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

/**
 * Created by moizali on 7/6/18.
 */

@Slf4j
@RestController
@RequestMapping(path = "notification/", produces = APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationController {

    @Autowired // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private InstanceRepository instanceRepository;

    @PostMapping(path = "/subscribe")
    public @ResponseBody
    InstanceDto addInstance(@Valid @RequestBody InstanceDto instanceDto) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        InstanceDto instanceDto1 = new InstanceDto();
        instanceDto1.setAppType(instanceDto.getAppType());
        instanceDto1.setPushToken(instanceDto.getPushToken());
        InstanceDto save = instanceRepository.save(instanceDto1);
        return save;
    }

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<InstanceDto> getAllUsers() {
        // This returns a JSON or XML with the users
        try {
            return instanceRepository.findAll();
        } catch (Exception exp) {
            exp.printStackTrace();
        }
        return null;
    }
}
