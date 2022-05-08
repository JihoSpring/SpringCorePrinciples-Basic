package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImple();

    @Test
    void join() {
        // given
        Long memberId = 1L;
        Member member = new Member(memberId, "alpha", Grade.VIP);

        // when
        memberService.join(member);
        Member foundMember = memberService.findMember(memberId);

        // then
        Assertions.assertThat(member).isEqualTo(foundMember);
    }

}
