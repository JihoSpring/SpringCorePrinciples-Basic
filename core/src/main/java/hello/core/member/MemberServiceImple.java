package hello.core.member;

public class MemberServiceImple implements MemberService {

    private final MemberRepository memberRepository = new MemoryMemeberRepository();

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
