package com.syh.example.rbacoopdemo.roleauth.member.repository;

import com.google.common.collect.Lists;
import com.syh.example.rbacoopdemo.roleauth.member.Member;
import com.syh.example.rbacoopdemo.roleauth.member.MemberKey;
import com.syh.example.rbacoopdemo.roleauth.member.MemberType;
import com.syh.example.rbacoopdemo.roleauth.member.repository.mapper.MemberDao;
import com.syh.example.rbacoopdemo.roleauth.member.repository.mapper.MemberRoleDao;
import com.syh.example.rbacoopdemo.roleauth.member.repository.po.MemberPo;
import com.syh.example.rbacoopdemo.roleauth.member.repository.po.MemberRolePo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author shen.yuhang
 * created on 2020/11/12
 **/
@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final MemberDao memberDao;
    private final MemberRoleDao memberRoleDao;

    public void save(Member member) {
        MemberPo memberPo = new MemberPo(member.getId(),
                member.getCompanyId(),
                member.getMemberType().name(),
                member.getOrgMemberId());

        memberDao.save(memberPo);

        List<MemberRolePo> objectRolePos = member.getRoleIds()
                .stream()
                .map(roleId -> new MemberRolePo(null, memberPo.getId(), roleId))
                .collect(Collectors.toList());
        memberRoleDao.deleteByMemberId(memberPo.getId());
        memberRoleDao.saveBatch(objectRolePos);

    }

    public Member selectByMemberKey(MemberKey key) {
        MemberPo memberPo = memberDao.selectByCompanyIdAndTypeAndOrgMemberId(key.getCompanyId(),
                key.getMemberType(), key.getOrgMemberId());

        if (memberPo == null) {
            return null;
        }

        return poToMembers(Collections.singletonList(memberPo))
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("poToMembers should not change the" +
                        "size of elements"));
    }

    public List<Member> selectByRole(Long roleId) {
        List<Long> memberIds = memberRoleDao.query()
                .select("member_id")
                .eq("role_id", roleId)
                .list()
                .stream()
                .map(MemberRolePo::getMemberId)
                .collect(Collectors.toList());

        if (memberIds.isEmpty()) {
            return Lists.newArrayList();
        }

        return poToMembers(memberDao.listByIds(memberIds));
    }

    private List<Member> poToMembers(List<MemberPo> pos) {
        return pos.stream()
                .map(po -> {
                    List<Long> roleIds = memberRoleDao.selectByMemberId(po.getId())
                            .stream()
                            .map(MemberRolePo::getRoleId)
                            .collect(Collectors.toList());

                    return new Member(
                            po.getId(),
                            po.getCompanyId(),
                            MemberType.valueOf(po.getMemberType()),
                            po.getOrgMemberId(),
                            roleIds);
                })
                .collect(Collectors.toList());
    }
}
