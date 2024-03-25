package hello.core.member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;//회원가입하고 조회하기 위해서는 저장소에서 멤버 정보를 가져와야하기 때문에 의존

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }


    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //bugTest용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}

