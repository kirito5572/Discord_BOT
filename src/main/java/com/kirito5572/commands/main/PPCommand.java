package com.kirito5572.commands.main;

import com.kirito5572.objects.main.EventPackage;
import com.kirito5572.objects.main.ICommand;
import me.duncte123.botcommons.messaging.EmbedUtils;
import net.dv8tion.jda.api.EmbedBuilder;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.List;

public class PPCommand implements ICommand {
    @Override
    public void handle(List<String> args, @Nonnull EventPackage event) {
        EmbedBuilder builder = EmbedUtils.getDefaultEmbed()
                .setTitle("리토봇 개인정보처리방침")
                .setColor(Color.RED)
                .setDescription("리토봇은 개인정보 보호법 제30조에 따라 정보주체의 개인정보를 보호하고 이와 관련한 고충을 신속하고 원활하게 처리할 수 있도록 하기 위하여 다음과 같이 개인정보 처리지침을 수립․공개합니다. ")
                .addField("제1조(개인정보의 처리목적)\n",
                        """
                                리토봇은 다음의 목적을 위하여 개인정보를 처리합니다. 처리하고 있는 개인정보는 다음의 목적 이외의 용도로는 이용되지 않으며, 이용 목적이 변경되는 경우에는 개인정보 보호법 제18조에 따라 별도의 동의를 받는 등 필요한 조치를 이행합니다.
                                1. 로그 처리
                                이 봇이 초대되어 있는 서버의 채팅 기록, 서버 변경 사항등을 수집, 처리합니다.
                                (당 기능은 설정에서 비활성화 할경우 적용되지 않습니다)
                                """, false)
                .addField("제2조(개인정보의 처리 및 보유기간)\n",
                        """
                                ① 리토봇은 법령에 따른 개인정보 보유․이용기간 또는 정보주체로부터 개인정보를 수집시에 동의받은 개인정보 보유․이용기간 내에서 개인정보를 처리․보유합니다.
                                ② 각각의 개인정보 처리 및 보유 기간은 다음과 같습니다.
                                1. 로그 처리 : 리토봇을 서버에서 추방하는 경우
                                """, false)
                .addField("제3조(개인정보처리의 위탁)\n",
                        """
                                ① 리토봇은 원활한 개인정보 업무처리를 위하여 다음과 같이 개인정보 처리업무를 위탁하고 있습니다. 1. 데이터 베이스 운영
                                  - 위탁받는 자(수탁자): AWS(Amazon Web Service)  - 위탁하는 업무의 내용: 로그 처리를 위한 데이터베이스 제공
                                ② 위탁업무의 내용이나 수탁자가 변경될 경우에는 지체없이 본 개인정보 처리방침을 통하여 공개하도록 하겠습니다.""", false)
                .addField("제4조(정보주체와 법정대리인의 권리․의무 및 행사방법)", """
                        ① 정보주체는 리토봇에 대해 언제든지 개인정보 열람․정정․삭제․처리정지 요구 등의 권리를 행사할 수 있습니다.
                           ② 제1항에 따른 권리 행사는 리토봇에 대해 개인정보보호법 시행령 제41조제1항에 따라 전자우편을 통하여 하실 수 있으며,  리토봇은 이에 대해 지체없이 조치하겠습니다.\s
                           ③ 제1항에 따른 권리 행사는 정보주체의 법정대리인이나 위임을 받은 자 등 대리인을 통하여 하실 수 있습니다. 이 경우 개인정보 보호법 시행규칙 별지 제11호 서식에 따른 위임장을 제출하셔야 합니다.\s
                           ④ 개인정보 열람 및 처리정지 요구는 개인정보보호법 제35조 제5항, 제37조 제2항에 의하여 정보주체의 권리가 제한 될 수 있습니다.\s
                           ⑤ 개인정보의 정정 및 삭제 요구는 다른 법령에서 그 개인정보가 수집 대상으로 명시되어 있는 경우에는 그 삭제를 요구할 수 없습니다.\s
                           ⑥ 리토봇은 정보주체 권리에 따른 열람의 요구, 정정·삭제의 요구, 처리정지의 요구 시 열람 등 요구를 한 자가 본인이거나 정당한 대리인인지를 확인합니다.\s""", false)
                .addField("제5조(처리하는 개인정보 항목)",
                        """
                                리토봇은 다음의 개인정보 항목을 처리하고 있습니다.\s
                                1. 로그 처리\s
                                    ․필수항목 : 전송자, 메세지 내용, 전송된 서버 ID
                                2. 유저 검색\s
                                    .필수항목 : 유저명, 유저ID, 가입일자, 서버 초대 일자, 서버 부여 역할, 온라인 상태, 봇 여부""", false)
                .addField("제6조(개인정보의 파기)\n",
                        """
                                 ① 리토봇은 개인정보 보유기간의 경과, 처리목적 달성 등 개인정보가 불필요하게 되었을 때에는 지체없이 해당 개인정보를 파기합니다.\s
                                   ② 정보주체로부터 동의받은 개인정보 보유기간이 경과하거나 처리목적이 달성되었음에도 불구하고 다른 법령에 따라 개인정보를 계속 보존하여야 하는 경우에는 따로 분리/보관합니다.\s
                                   ③ 개인정보 파기의 절차 및 방법은 다음과 같습니다.\s
                                   1. 파기절차\s
                                     리토봇은 파기 사유가 발생한 개인정보를 선정하고, 리토봇의 개인정보 보호책임자의 승인을 받아 개인정보를 파기합니다.\s
                                   2. 파기방법\s
                                     리토봇은 전자적 파일 형태로 기록․저장된 개인정보는 기록을 재생할 수 없도록 파기하며, 종이 문서에 기록․저장된 개인정보는 분쇄기로 분쇄하거나 소각하여 파기합니다. \
                                """, false)
                .addField("제7조(개인정보의 안전성 확보조치)\n",
                        """
                                리토봇은 개인정보의 안전성 확보를 위해 다음과 같은 조치를 취하고 있습니다.\s
                                   1. 관리적 조치 : DB 유저명/비밀번호 유출 방지\s
                                   2. 기술적 조치 : DB 와의 통신 암호화등\s
                                """, false)
                .addField("제8조(개인정보 보호책임자)\n",
                        """
                                ① 리토봇은 개인정보 처리에 관한 업무를 총괄해서 책임지고, 개인정보 처리와 관련한 정보주체의 불만처리 및 피해구제 등을 위하여 아래와 같이 개인정보 보호책임자를 지정하고 있습니다.\s

                                   ▶ 개인정보 보호책임자\s
                                       성명 : LiODi5572(Kim Hyeokjin)\s
                                       직책 : 제작자\s
                                       연락처 : first43013@gmail.com, (010-3914-2701)\s
                                  ② 정보주체께서는 리토봇의 서비스를 이용하시면서 발생한 모든 개인정보 보호 관련 문의, 불만처리, 피해구제 등에 관한 사항을 개인정보 보호책임자에게 문의하실 수 있습니다. 리토봇은 정보주체의 문의에 대해 지체없이 답변 및 처리해드릴 것입니다.\s""", false)
                .addField("제9조(개인정보 열람청구)\n",
                        """
                                 정보주체는 개인정보 보호법 제35조에 따른 개인정보의 열람 청구를 아래의 부서에 할 수 있습니다. 리토봇은 정보주체의 개인정보 열람청구가 신속하게 처리되도록 노력하겠습니다.\s
                                   ▶ 개인정보 보호책임자\s
                                       성명 : LiODi5572(Kim Hyeokjin)\s
                                       직책 : 봇 제작자\s
                                       연락처 : first43013@gmail.com, (010-5762-1701)\s
                                """, false)
                .addField("제10조(권익침해 구제방법)\n",
                        """
                                정보주체는 아래의 기관에 대해 개인정보 침해에 대한 피해구제, 상담 등을 문의하실 수 있습니다.\s

                                  <아래의 기관은 리토봇 과는 별개의 기관으로서, 리토봇의 자체적인 개인정보 불만처리, 피해구제 결과에 만족하지 못하시거나 보다 자세한 도움이 필요하시면 문의하여 주시기 바랍니다>

                                   ▶ 개인정보 침해신고센터 (한국인터넷진흥원 운영)\s
                                     - 소관업무 : 개인정보 침해사실 신고, 상담 신청\s
                                     - 홈페이지 : privacy.kisa.or.kr\s
                                     - 전화 : (국번없이) 118\s
                                     - 주소 : (58324) 전남 나주시 진흥길 9(빛가람동 301-2) 3층 개인정보침해신고센터

                                   ▶ 개인정보 분쟁조정위원회
                                     - 소관업무 : 개인정보 분쟁조정신청, 집단분쟁조정 (민사적 해결)\s
                                     - 홈페이지 : www.kopico.go.kr\s
                                     - 전화 : (국번없이) 1833-6972
                                     - 주소 : (03171)서울특별시 종로구 세종대로 209 정부서울청사 4층

                                   ▶ 대검찰청 사이버범죄수사단 : 02-3480-3573 (www.spo.go.kr)

                                   ▶ 경찰청 사이버안전국 : 182 (https://cyberbureau.police.go.kr)""", false)
                .addField("제11조(개인정보 처리방침 변경)", "① 이 개인정보 처리방침은 2020-01-06부터 적용됩니다.", false);
        event.getChannel().sendMessageEmbeds(builder.build()).queue();
    }

    @Override
    public String getHelp() {
        return "당 봇의 개인정보처리방침을 안내합니다.";
    }

    @Override
    public String getInvoke() {
        return "개인정보처리방침";
    }

    @Override
    public String getSmallHelp() {
        return "other";
    }
}
