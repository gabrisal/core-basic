package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    // 구체화에 의존함, DIP 위반
//    private final MemberRepository memberRepository = new MemoryMemberRepository();

    // AppConfig 에서 관리 - 추상화에만 의존
    private final MemberRepository memberRepository;

    // 자동으로 의존관계 주입
    @Autowired // = ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
