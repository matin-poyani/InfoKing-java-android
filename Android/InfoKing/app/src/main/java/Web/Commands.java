package Web;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import Models.StructArchive;
import Models.StructCategories;
import Models.StructCategory;
import Models.StructDetail;
import Models.StructQuest;
import Models.StructQuestion;
import Models.StructUser;
import ir.ncis.infoking.App;

public class Commands {
    public static boolean answer(int questId, int userId, int question, int answer) {
        boolean result = false;
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "answer");
        params.put("quest_id", String.valueOf(questId));
        params.put("user_id", String.valueOf(userId));
        params.put("q", String.valueOf(question));
        switch (answer) {
            case 1:
                params.put("a", "a");
                break;
            case 2:
                params.put("a", "b");
                break;
            case 3:
                params.put("a", "c");
                break;
            case 4:
                params.put("a", "d");
                break;
        }
        String json = WebService.post(params);
        if (json != null) {
            try {
                result = new JSONObject(json).getInt("code") == 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static StructCategories categoryGet() {
        StructCategories result = null;
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "category_get");
        String json = WebService.post(params);
        if (json != null) {
            try {
                JSONObject obj = new JSONObject(json);
                if (obj.getInt("code") == 1) {
                    result = new StructCategories();
                    JSONObject data = obj.getJSONObject("data");
                    result.group1 = new ArrayList<>();
                    JSONArray group1 = data.getJSONArray("group1");
                    int len1 = group1.length();
                    for (int i = 0; i < len1; i++) {
                        JSONObject dataItem = group1.getJSONObject(i);
                        StructCategory group1Item = new StructCategory();
                        group1Item.id = dataItem.getInt("id");
                        group1Item.name = dataItem.getString("name");
                        result.group1.add(group1Item);
                    }
                    result.group2 = new ArrayList<>();
                    JSONArray group2 = data.getJSONArray("group2");
                    int len2 = group2.length();
                    for (int i = 0; i < len2; i++) {
                        JSONObject dataItem = group2.getJSONObject(i);
                        StructCategory group2Item = new StructCategory();
                        group2Item.id = dataItem.getInt("id");
                        group2Item.name = dataItem.getString("name");
                        result.group2.add(group2Item);
                    }
                    result.group3 = new ArrayList<>();
                    JSONArray group3 = data.getJSONArray("group3");
                    int len3 = group3.length();
                    for (int i = 0; i < len3; i++) {
                        JSONObject data3Item = group3.getJSONObject(i);
                        StructCategory group3Item = new StructCategory();
                        group3Item.id = data3Item.getInt("id");
                        group3Item.name = data3Item.getString("name");
                        result.group3.add(group3Item);
                    }
                }
            } catch (JSONException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static boolean categorySet(int questId, int userId, int cat1Id, int cat2Id, int cat3Id) {
        boolean result = false;
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "category_set");
        params.put("quest_id", String.valueOf(questId));
        params.put("user_id", String.valueOf(userId));
        params.put("cat1_id", String.valueOf(cat1Id));
        params.put("cat2_id", String.valueOf(cat2Id));
        params.put("cat3_id", String.valueOf(cat3Id));
        String json = WebService.post(params);
        if (json != null) {
            try {
                result = new JSONObject(json).getInt("code") == 1;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static StructUser login(String user, String pass) {
        StructUser result = null;
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "login");
        params.put("user", user);
        params.put("pass", pass);
        String json = WebService.post(params);
        if (json != null) {
            try {
                JSONObject obj = new JSONObject(json);
                if (obj.getInt("code") == 1) {
                    result = new StructUser();
                    JSONObject data = obj.getJSONObject("data");
                    result.id = data.getInt("id");
                    result.name = data.getString("name");
                    result.searching = data.getBoolean("searching");
                }
            } catch (JSONException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static ArrayList<StructArchive> questArchive(int userId) {
        ArrayList<StructArchive> result = new ArrayList<>();
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "quest_archive");
        params.put("user_id", String.valueOf(userId));
        String json = WebService.post(params);
        if (json != null) {
            try {
                JSONObject obj = new JSONObject(json);
                if (obj.getInt("code") == 1) {
                    JSONArray data = obj.getJSONArray("data");
                    int len = data.length();
                    for (int i = 0; i < len; i++) {
                        JSONObject item = data.getJSONObject(i);
                        StructArchive archive = new StructArchive();
                        archive.id = item.getInt("id");
                        archive.opponent = item.getString("opponent");
                        archive.score_user = item.getInt("score_user");
                        archive.score_opponent = item.getInt("score_opponent");
                        archive.result = item.getString("result");
                        result.add(archive);
                    }
                }
            } catch (JSONException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static boolean questCancel(int userId) {
        boolean result = false;
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "quest_cancel");
        params.put("user_id", String.valueOf(userId));
        String json = WebService.post(params);
        if (json != null) {
            try {
                result = new JSONObject(json).getInt("code") == 1;
            } catch (JSONException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static StructQuest questCheck(int userId) {
        StructQuest result = null;
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "quest_check");
        params.put("user_id", String.valueOf(userId));
        String json = WebService.post(params);
        if (json != null) {
            try {
                JSONObject obj = new JSONObject(json);
                if (obj.getInt("code") == 1) {
                    result = new StructQuest();
                    JSONObject data = obj.getJSONObject("data");
                    result.id = data.getInt("id");
                    result.opponent = data.getString("opponent");
                    result.q1 = data.getInt("q1");
                    result.q2 = data.getInt("q2");
                    result.q3 = data.getInt("q3");
                    result.q4 = data.getInt("q4");
                    result.q5 = data.getInt("q5");
                    result.q6 = data.getInt("q6");
                }
            } catch (JSONException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static StructDetail questDetail(int questId, int userId) {
        StructDetail result = null;
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "quest_detail");
        params.put("quest_id", String.valueOf(questId));
        params.put("user_id", String.valueOf(userId));
        String json = WebService.post(params);
        if (json != null) {
            try {
                JSONObject obj = new JSONObject(json);
                if (obj.getInt("code") == 1) {
                    result = new StructDetail();
                    JSONObject data = obj.getJSONObject("data");
                    result.user1 = data.getString("user1");
                    result.user2 = data.getString("user2");
                    JSONObject q1 = data.getJSONObject("q1");
                    if (q1 != null) {
                        result.q1 = new StructQuestion();
                        result.q1.question = q1.getString("question");
                        result.q1.a = q1.getString("a");
                        result.q1.b = q1.getString("b");
                        result.q1.c = q1.getString("c");
                        result.q1.d = q1.getString("d");
                        result.q1.answer = q1.getString("answer");
                        result.q1.you = q1.getString("you");
                    }
                    JSONObject q2 = data.getJSONObject("q2");
                    if (q2 != null) {
                        result.q2 = new StructQuestion();
                        result.q2.question = q2.getString("question");
                        result.q2.a = q2.getString("a");
                        result.q2.b = q2.getString("b");
                        result.q2.c = q2.getString("c");
                        result.q2.d = q2.getString("d");
                        result.q2.answer = q2.getString("answer");
                        result.q2.you = q2.getString("you");
                    }
                    JSONObject q3 = data.getJSONObject("q3");
                    if (q3 != null) {
                        result.q3 = new StructQuestion();
                        result.q3.question = q3.getString("question");
                        result.q3.a = q3.getString("a");
                        result.q3.b = q3.getString("b");
                        result.q3.c = q3.getString("c");
                        result.q3.d = q3.getString("d");
                        result.q3.answer = q3.getString("answer");
                        result.q3.you = q3.getString("you");
                    }
                    JSONObject q4 = data.getJSONObject("q4");
                    if (q4 != null) {
                        result.q4 = new StructQuestion();
                        result.q4.question = q4.getString("question");
                        result.q4.a = q4.getString("a");
                        result.q4.b = q4.getString("b");
                        result.q4.c = q4.getString("c");
                        result.q4.d = q4.getString("d");
                        result.q4.answer = q4.getString("answer");
                        result.q4.you = q4.getString("you");
                    }
                    JSONObject q5 = data.getJSONObject("q5");
                    if (q5 != null) {
                        result.q5 = new StructQuestion();
                        result.q5.question = q5.getString("question");
                        result.q5.a = q5.getString("a");
                        result.q5.b = q5.getString("b");
                        result.q5.c = q5.getString("c");
                        result.q5.d = q5.getString("d");
                        result.q5.answer = q5.getString("answer");
                        result.q5.you = q5.getString("you");
                    }
                    JSONObject q6 = data.getJSONObject("q6");
                    if (q6 != null) {
                        result.q6 = new StructQuestion();
                        result.q6.question = q6.getString("question");
                        result.q6.a = q6.getString("a");
                        result.q6.b = q6.getString("b");
                        result.q6.c = q6.getString("c");
                        result.q6.d = q6.getString("d");
                        result.q6.answer = q6.getString("answer");
                        result.q6.you = q6.getString("you");
                    }
                    result.finished = data.getInt("finished") == 1;
                }
            } catch (JSONException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static StructQuestion question(int questionId) {
        StructQuestion result = null;
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "question");
        params.put("question_id", String.valueOf(questionId));
        String json = WebService.post(params);
        if (json != null) {
            try {
                JSONObject obj = new JSONObject(json);
                if (obj.getInt("code") == 1) {
                    JSONObject data = obj.getJSONObject("data");
                    result = new StructQuestion();
                    result.question = data.getString("question");
                    result.a = data.getString("a");
                    result.b = data.getString("b");
                    result.c = data.getString("c");
                    result.d = data.getString("d");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static StructQuest questNew(int userId) {
        StructQuest result = null;
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "quest_new");
        params.put("user_id", String.valueOf(userId));
        String json = WebService.post(params);
        if (json != null) {
            try {
                JSONObject obj = new JSONObject(json);
                if (obj.getInt("code") == 1) {
                    String status = obj.getString("data");
                    if (status.equals("searching")) {
                        App.CURRENT_USER.searching = true;
                    } else {
                        result = new StructQuest();
                        JSONObject data = new JSONObject(status);
                        result.id = data.getInt("id");
                        result.opponent = data.getString("opponent");
                        result.q1 = data.getInt("q1");
                        result.q2 = data.getInt("q2");
                        result.q3 = data.getInt("q3");
                        result.q4 = data.getInt("q4");
                        result.q5 = data.getInt("q5");
                        result.q6 = data.getInt("q6");
                    }
                }
            } catch (JSONException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public static boolean register(String name, String user, String pass) {
        boolean result = false;
        HashMap<String, String> params = new HashMap<>();
        params.put("action", "register");
        params.put("name", name);
        params.put("user", user);
        params.put("pass", pass);
        String json = WebService.post(params);
        if (json != null) {
            try {
                result = new JSONObject(json).getInt("code") == 1;
            } catch (JSONException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
