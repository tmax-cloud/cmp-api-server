package com.tmax.cmp.entity.aws.ec2;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
public class GroupIdentifier {

    private String groupName;

    private String groupId;
}
